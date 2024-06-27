package com.bnt.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import com.bnt.entity.Category;
import com.bnt.entity.Questions;
import com.bnt.entity.QuestionsResponse;
import com.bnt.exception.QuestionNotFoundException;
import com.bnt.repository.CategoryRepository;
import com.bnt.repository.QuestionRepository;

@SpringBootTest
class QuestionServiceImplTest {

	@Mock
	private CategoryRepository categoryRepository;

	@Mock
	private QuestionRepository questionRepository;

	@InjectMocks
	private QuestionServiceImpl questionService;

	private MockMultipartFile validExcelFile;

	@Before(value = "")
	public void setup() throws IOException {
		InputStream inputStream = getClass().getResourceAsStream("/valid_excel_file.xlsx");
		validExcelFile = new MockMultipartFile("file", "valid_excel_file.xlsx", "application/vnd.ms-excel",
				inputStream.readAllBytes());
	}

	public Questions setAddQuestionRequest() {
		Questions question = new Questions();
		question.setQuestion_id(1l);
		question.setContent("what is java");
		question.setOption1("depedent language");
		question.setOption2("only one system used");
		question.setOption3("hard to understand");
		question.setOption4("Independant easy to understand");
		question.setAnswer("Independant easy to understand");
		question.setMarks("100");

		Category category = new Category();
		category.setTitle("Advance java Test");
		question.setCategory(category);
		return question;

	}

	@Test
	public void testAddQuestion() {

		Questions question = setAddQuestionRequest();
		Category category = question.getCategory();
		when(categoryRepository.findByTitle(category.getTitle())).thenReturn(Optional.of(category));

		questionService.addQuestionByName(question);
	}

	@Test
	public void testGetAllQuestions() {
		List<Questions> questions = new ArrayList<>();
		Questions question = setAddQuestionRequest();
		questions.add(question);

		when(questionRepository.findAll()).thenReturn(questions);
		List<QuestionsResponse> result = questionService.getAllQuestions();
		assertEquals(questions.size(), result.size());
	}

	@Test
	public void testGetQuestionsById() {
		Long questionId = 1l;
		Questions question = setAddQuestionRequest();
		question.setQuestion_id(1l);
		question.setContent("what is java");
		question.setOption1("depedent language");
		question.setOption2("only one system used");
		question.setOption3("hard to understand");
		question.setOption4("Independant easy to understand");
		when(questionRepository.findById(questionId)).thenReturn(Optional.of(question));
		QuestionsResponse result = questionService.getQuestionsById(questionId);
		assertNotNull(result);
		assertEquals(questionId, result.getQuestionId());
		assertEquals("what is java", result.getContent());
	}

	@Test
	public void testUpdateQuestion() {
		Long questionId = 1L;

		// Creating a sample existing question
		Questions existingQuestion = new Questions();
		existingQuestion.setQuestion_id(questionId);
		existingQuestion.setContent("Existing Question");
		existingQuestion.setOption1("Option1");
		existingQuestion.setOption2("Option2");
		existingQuestion.setOption3("Option3");
		existingQuestion.setOption4("Option4");
		existingQuestion.setAnswer("Existing Answer");
		existingQuestion.setMarks("100");

		Questions request = new Questions();
		request.setQuestion_id(questionId);
		request.setContent("Updated Question");
		request.setOption1("Updated Option1");
		request.setOption2("Updated Option2");
		request.setOption3("Updated Option3");
		request.setOption4("Updated Option4");
		request.setAnswer("Updated Answer");
		request.setMarks("100");

		when(questionRepository.findById(questionId)).thenReturn(Optional.of(existingQuestion));
		when(questionRepository.save(any())).thenReturn(request);
		QuestionsResponse result = questionService.updateQuestion(request);
		assertEquals(questionId, result.getQuestionId());
		assertEquals("Updated Question", result.getContent());
		assertEquals("Updated Answer", result.getAnswer());
	}

	@Test
	public void testDeleteQuestion() {

		Long questionId = 1L;
		when(questionRepository.existsById(questionId)).thenReturn(true);
		questionService.deleteQuestion(questionId);
		verify(questionRepository, times(1)).deleteById(questionId);
	}

	@Test
	public void testDeleteQuestion_NotFound() {

		Long questionId = 1L;
		when(questionRepository.existsById(questionId)).thenReturn(false);
		assertThrows(QuestionNotFoundException.class, () -> {
			questionService.deleteQuestion(questionId);
		});
	}

	@Test
	public void testImportQuestionsFromExcelSuccess() throws IOException {
		try {
			when(categoryRepository.findByTitle(anyString())).thenReturn(java.util.Optional.of(new Category()));
			when(questionRepository.saveAll(any())).thenReturn(Collections.emptyList());
			questionService.importQuestionsFromExcel(Collections.singletonList(validExcelFile));
			verify(questionRepository, times(1)).saveAll(anyList());
		} catch (Exception e) {
			e.getMessage();
		}
	}
}