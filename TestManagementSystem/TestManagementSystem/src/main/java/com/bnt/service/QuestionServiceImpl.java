package com.bnt.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bnt.entity.Category;
import com.bnt.entity.Questions;
import com.bnt.entity.QuestionsResponse;
import com.bnt.exception.CategoryNotFoundException;
import com.bnt.exception.QuestionNotFoundException;
import com.bnt.repository.CategoryRepository;
import com.bnt.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	private final CategoryRepository categoryRepository;
	private final QuestionRepository questionRepository;

	public QuestionServiceImpl(CategoryRepository categoryRepository, QuestionRepository questionRepository) {
		this.categoryRepository = categoryRepository;
		this.questionRepository = questionRepository;
	}

	@Override
	public Questions addQuestionByName(Questions question) {

		Category category = categoryRepository.findByTitle(question.getCategory().getTitle())
				.orElseThrow(() -> new CategoryNotFoundException("Category not found"));

		Category categeries = new Category();
		categeries.setCategoryId(category.getCategoryId());

		Questions questionRequest = new Questions();
		questionRequest.setContent(question.getContent());
		questionRequest.setOption1(question.getOption1());
		questionRequest.setOption2(question.getOption2());
		questionRequest.setOption3(question.getOption3());
		questionRequest.setOption4(question.getOption4());
		questionRequest.setAnswer(question.getAnswer());
		questionRequest.setMarks(question.getMarks());
		questionRequest.setCategory(categeries);
		return questionRepository.save(questionRequest);
	}

	@Override
	public List<QuestionsResponse> getAllQuestions() {
		List<Questions> questions = questionRepository.findAll();
		List<QuestionsResponse> questionsResponses = new ArrayList<>();

		for (Questions question : questions) {
			questionsResponses.add(question.toResponse());
		}

		return questionsResponses;
	}

	@Override
	public QuestionsResponse getQuestionsById(Long questionId) {
		Questions question = questionRepository.findById(questionId).orElse(null);

		if (question == null) {
			throw new QuestionNotFoundException("Question not found");
		}

		return convertToCategoryResponse(question);
	}

	@Override
	public QuestionsResponse updateQuestion(Questions request) {
		try {
			Questions existingQuestion = questionRepository.findById(request.getQuestion_id())
					.orElseThrow(() -> new QuestionNotFoundException("Question not found"));
			existingQuestion.setQuestion_id(request.getQuestion_id());
			existingQuestion.setContent(request.getContent());
			existingQuestion.setOption1(request.getOption1());
			existingQuestion.setOption2(request.getOption2());
			existingQuestion.setOption3(request.getOption3());
			existingQuestion.setOption4(request.getOption4());
			existingQuestion.setAnswer(request.getAnswer());
			existingQuestion.setMarks(request.getMarks());

			Questions response = questionRepository.save(existingQuestion);
			return convertToCategoryResponse(response);
		} catch (Exception e) {
			throw new QuestionNotFoundException("Failed to update question" + e);
		}
	}

	@Override
	public void deleteQuestion(Long questionId) {
		if (!questionRepository.existsById(questionId)) {
			throw new QuestionNotFoundException("Question not found with id: " + questionId);
		}
		questionRepository.deleteById(questionId);
	}

	private QuestionsResponse convertToCategoryResponse(Questions questions) {
		QuestionsResponse response = new QuestionsResponse();
		response.setQuestionId(questions.getQuestion_id());
		response.setContent(questions.getContent());
		response.setOption1(questions.getOption1());
		response.setOption2(questions.getOption2());
		response.setOption3(questions.getOption3());
		response.setOption4(questions.getOption4());
		response.setAnswer(questions.getAnswer());
		response.setMarks(questions.getMarks());
		return response;
	}

	@Override
	public void importQuestionsFromExcel(List<MultipartFile> multipartfiles) throws IOException {
		for (MultipartFile multipartFile : multipartfiles) {
			try {
				processExcelFile(multipartFile.getInputStream());
			} catch (Exception e) {
				System.err.println("Error: Not a valid Office Open XML file: " + multipartFile.getOriginalFilename());
			}
		}
	}

	public void processExcelFile(InputStream inputStream) throws IOException {
		List<Questions> transactions = new ArrayList<>();
		try (XSSFWorkbook workBook = new XSSFWorkbook(inputStream)) {
			XSSFSheet sheet = workBook.getSheetAt(0);
			for (int rowIndex = 0; rowIndex < getNumberOfNonEmptyCells(sheet, 0); rowIndex++) {
				XSSFRow row = sheet.getRow(rowIndex);
				if (rowIndex == 0) {
					continue;
				}

				String content = String.valueOf(row.getCell(0));
				String option1 = String.valueOf(row.getCell(1));
				String option2 = String.valueOf(row.getCell(2));
				String option3 = String.valueOf(row.getCell(3));
				String option4 = String.valueOf(row.getCell(4));
				String answer = String.valueOf(row.getCell(5));
				String mark = String.valueOf(row.getCell(6));
				String title = String.valueOf(row.getCell(7));

				Category category = categoryRepository.findByTitle(title)
						.orElseGet(() -> categoryRepository.save(new Category(title)));

				Questions questionRequest = new Questions();
				questionRequest.setContent(content);
				questionRequest.setOption1(option1);
				questionRequest.setOption2(option2);
				questionRequest.setOption3(option3);
				questionRequest.setOption4(option4);
				questionRequest.setAnswer(answer);
				questionRequest.setMarks(mark);
				questionRequest.setCategory(category);
				transactions.add(questionRequest);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (!transactions.isEmpty()) {
			questionRepository.saveAll(transactions);
		}
	}

	public static int getNumberOfNonEmptyCells(XSSFSheet sheet, int columnIndex) {
		int numOfNonEmptyCells = 0;
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			if (row != null) {
				XSSFCell cell = row.getCell(columnIndex);
				if (cell != null && cell.getCellType() != CellType.BLANK) {
					numOfNonEmptyCells++;
				}
			}
		}
		return numOfNonEmptyCells;
	}

}
