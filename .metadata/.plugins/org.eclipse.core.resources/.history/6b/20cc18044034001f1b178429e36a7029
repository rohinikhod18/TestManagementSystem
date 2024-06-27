package com.bnt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "questionsTest")
public class QuestionsTests {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long questionTestId;

	@ManyToOne
    @JoinColumn(name = "test_id")
    public Tests tests;

    @ManyToOne
    @JoinColumn(name = "question_id")
    public Questions Questions;

	public Long getQuestionTestId() {
		return questionTestId;
	}

	public void setQuestionTestId(Long questionTestId) {
		this.questionTestId = questionTestId;
	}

	public Tests getTests() {
		return tests;
	}

	public void setTests(Tests tests) {
		this.tests = tests;
	}

	public Questions getQuestions() {
		return Questions;
	}

	public void setQuestions(Questions questions) {
		Questions = questions;
	}
}
