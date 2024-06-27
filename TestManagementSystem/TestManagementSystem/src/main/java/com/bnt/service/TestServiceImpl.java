package com.bnt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.entity.Tests;
import com.bnt.exception.TestIdNotExistException;
import com.bnt.repository.TestRepository;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository testRepository;

	@Override
	public Tests addTest(Tests test) {
		return testRepository.save(test);
	}

	@Override
	public List<Tests> getAllTest() {
		return testRepository.findAll();
	}

	@Override
	public Tests getTestById(Long id) {
		Tests findTestId = testRepository.findById(id).orElse(null);
		if (findTestId == null) {
			throw new TestIdNotExistException("Question not found");
		}

		return findTestId;
	}

	@Override
	public Tests updateTest(Tests test) {
		try {
			Tests existingQuestion = testRepository.findById(test.getTest_id())
					.orElseThrow(() -> new TestIdNotExistException("Question not found"));
			existingQuestion.setTitle(test.getTitle());
			existingQuestion.setDescription(test.getDescription());
			existingQuestion.setMaxMarks(test.getMaxMarks());
			existingQuestion.isActive();
			existingQuestion.setNumberOfQuestions(test.getNumberOfQuestions());

			return testRepository.save(existingQuestion);
		} catch (Exception e) {
			throw new TestIdNotExistException("Failed to update question" + e);
		}
	}

	@Override
	public void deleteTest(Long testId) {
		if (testRepository.existsById(testId)) {
			try {
				testRepository.deleteById(testId);
			} catch (TestIdNotExistException e) {
				throw new TestIdNotExistException("Failed to delete testId");
			}
		} else {
			throw new TestIdNotExistException("test with ID " + testId + " not found");
		}
	}

}
