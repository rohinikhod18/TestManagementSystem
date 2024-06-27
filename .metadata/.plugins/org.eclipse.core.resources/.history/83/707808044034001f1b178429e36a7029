package com.bnt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bnt.entity.Questions;
import com.bnt.entity.QuestionsTests;
import com.bnt.entity.Tests;
import com.bnt.repository.QuestionRepository;
import com.bnt.repository.QuestionTestRepository;
import com.bnt.repository.TestRepository;

@Service
public class AddQuestionTestServiceImpl implements AddQuestionTestService {

	private final TestRepository testRepository;

	private final QuestionRepository questionRepository;

	private final QuestionTestRepository questionTestRepository;

	public AddQuestionTestServiceImpl(TestRepository testRepository, QuestionRepository questionRepository,
			QuestionTestRepository questionTestRepository) {
		super();
		this.testRepository = testRepository;
		this.questionRepository = questionRepository;
		this.questionTestRepository = questionTestRepository;
	}

	@Override
	public QuestionsTests addQuestionsById(Long testId, Long questionId) {

		Questions question = questionRepository.findById(questionId).orElse(null);

		Tests test = testRepository.findById(testId).orElse(null);

		QuestionsTests questionTest = new QuestionsTests();
		questionTest.setTests(test);
		questionTest.setQuestions(question);

		return questionTestRepository.save(questionTest);
	}

	@Override
	public List<QuestionsTests> getQuestionsTestList() {

		return questionTestRepository.findAll();
	}

}
