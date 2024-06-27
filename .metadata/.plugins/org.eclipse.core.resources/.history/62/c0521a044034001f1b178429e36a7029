package com.bnt.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
@Table(name = "tests")
public class Tests {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long test_id;

	private String title;
	private String description;
	private int maxMarks;
	private int numberOfQuestions;
	private boolean active = false;

	@ManyToMany
	@JoinTable(name = "questionsTest", joinColumns = @JoinColumn(name = "test_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
	private List<Questions> questions;

	public Tests() {
		super();
	}

	public Long getTest_id() {
		return test_id;
	}


	public void setTest_id(Long test_id) {
		this.test_id = test_id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title == null || title.trim().isEmpty()) {
			throw new IllegalArgumentException("Title cannot be null or blank");
		}
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description == null || description.trim().isEmpty()) {
			throw new IllegalArgumentException("Description cannot be null or blank");
		}
		this.description = description;
	}

	public int getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(int maxMarks) {
		if (maxMarks < 0) {
			throw new IllegalArgumentException("Maximum marks cannot be negative");
		}
		this.maxMarks = maxMarks;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		if (numberOfQuestions < 0) {
			throw new IllegalArgumentException("Number of questions cannot be negative");
		}
		this.numberOfQuestions = numberOfQuestions;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}

}
