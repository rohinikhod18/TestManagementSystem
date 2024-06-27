package com.bnt.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bnt.entity.QuestionsTests;
import com.bnt.service.AddQuestionTestService;

@SpringBootTest
class QuestionTestsControllerTest {

	@InjectMocks
	private QuestionTestsController controller;
	@Mock
    private AddQuestionTestService questionService;


    @Test
    public void testAddQuestionToTest_Positive() {
        Long testId = 1L;
        Long questionId = 2L;
        QuestionsTests expectedTest = new QuestionsTests();

        when(questionService.addQuestionsById(testId, questionId)).thenReturn(expectedTest);

        ResponseEntity<QuestionsTests> response = controller.addQuestionToTest(testId, questionId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedTest, response.getBody());
    }

    @Test
    public void testGetAllQuestionsTest() {

        List<QuestionsTests> expectedTests = new ArrayList<>();
        when(questionService.getQuestionsTestList()).thenReturn(expectedTests);

        List<QuestionsTests> result = controller.getAllQuestionsTest();

        assertEquals(expectedTests, result);
    }

}
