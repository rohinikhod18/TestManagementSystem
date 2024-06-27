package com.bnt.entity;

public class QuestionsTestDTO {

    private Long questionTestId;
    private Long testId;
    private Long questionId;

    public QuestionsTestDTO() {
        // Default constructor
    }

    // Constructor with all fields
    public QuestionsTestDTO(Long questionTestId, Long testId, Long questionId) {
        this.questionTestId = questionTestId;
        this.testId = testId;
        this.questionId = questionId;
    }

    // Getters and setters
    public Long getQuestionTestId() {
        return questionTestId;
    }

    public void setQuestionTestId(Long questionTestId) {
        this.questionTestId = questionTestId;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
