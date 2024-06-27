package com.bnt.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "questions")
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long question_id;

    private String content;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private String marks;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "questions", cascade = CascadeType.ALL)
    private List<Tests> tests;

	public List<Tests> getTests() {
		return tests;
	}

	public void setTests(List<Tests> tests) {
		this.tests = tests;
	}

	public Questions() {
		super();
	}

	public Long getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Long question_id) {
		this.question_id = question_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if (content == null || content.trim().isEmpty()) {
	        throw new IllegalArgumentException("Content cannot be null or blank");
	    }
		this.content = content;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		if (option1 == null || option1.trim().isEmpty()) {
	        throw new IllegalArgumentException("Option1 cannot be null or blank");
	    }
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		if (marks == null || marks.trim().isEmpty()) {
	        throw new IllegalArgumentException("Content cannot be null or blank");
	    }
		this.marks = marks;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		if (category == null) {
	        throw new IllegalArgumentException("Category cannot be null");
	    }
		this.category = category;
	}

	public QuestionsResponse toResponse() {
		return new QuestionsResponse(question_id, content, option1, option2, option3, option4, answer, marks);
	}

}
