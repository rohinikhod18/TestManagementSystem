package com.bnt.entity;

import java.util.List;

public class TestsDTO {

    private Long test_id;
    private String title;
    private String description;
    private int maxMarks;
    private int numberOfQuestions;
    private boolean active;
    private List<Questions> questions;

    public TestsDTO() {
        // Default constructor
    }

	public List<Questions> getQuestions() {
		return questions;
	}


	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}

	public TestsDTO(Long test_id, String title, String description, int maxMarks, int numberOfQuestions,
			boolean active) {
		super();
		this.test_id = test_id;
		this.title = title;
		this.description = description;
		this.maxMarks = maxMarks;
		this.numberOfQuestions = numberOfQuestions;
		this.active = active;
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
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(int maxMarks) {
        this.maxMarks = maxMarks;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


	/*
	 * public List<Questions> getQuestions() { return questions; }
	 * 
	 * 
	 * public void setQuestions(List<Questions> questions) { this.questions =
	 * questions; }
	 */

  
}
