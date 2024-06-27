package com.bnt.entity;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TestResponse {
	
	private Long testId;
    private List<Questions> questionIds;
	   
	public Long getTestId() {
		return testId;
	}
	
	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public List<Questions> getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(List<Questions> questionIds) {
		this.questionIds = questionIds;
	}
	
}
