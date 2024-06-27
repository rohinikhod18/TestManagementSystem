package com.bnt.service;

import java.util.List;

import com.bnt.entity.QuestionsTests;

public interface AddQuestionTestService {

	public QuestionsTests addQuestionsById(Long testId, Long questionId);
	public List<QuestionsTests> getQuestionsTestList();


}
