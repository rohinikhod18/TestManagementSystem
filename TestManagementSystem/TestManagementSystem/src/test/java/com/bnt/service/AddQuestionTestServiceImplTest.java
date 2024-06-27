package com.bnt.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.bnt.entity.Category;
import com.bnt.entity.Questions;
import com.bnt.entity.QuestionsTests;
import com.bnt.entity.Tests;
import com.bnt.repository.QuestionRepository;
import com.bnt.repository.QuestionTestRepository;
import com.bnt.repository.TestRepository;

@SpringBootTest
class AddQuestionTestServiceImplTest {

	@Mock
    private TestRepository testRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuestionTestRepository questionTestRepository;

    @InjectMocks
    private AddQuestionTestServiceImpl questionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddQuestionsById() {
        // Create mock test and question objects
        Tests test = new Tests();
        test.setTest_id(1L);
        // Set test properties

        Category category = new Category();   
        category.setTitle("Spring boot");

        Questions question = new Questions();
        question.setQuestion_id(1L);
        // Set question properties

        QuestionsTests questionTest = new QuestionsTests();
        questionTest.setQuestions(question);
        questionTest.setTests(test);

        when(testRepository.findById(1L)).thenReturn(Optional.of(test));
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question)); // Corrected ID
        when(questionTestRepository.save(any(QuestionsTests.class))).thenReturn(questionTest);

        // Call the service method
        QuestionsTests result = questionService.addQuestionsById(1L, 1L); // Corrected ID

        // Verify that the repository methods were called with the correct parameters
        verify(testRepository, times(1)).findById(1L);
        verify(questionRepository, times(1)).findById(1L); // Corrected ID
        verify(questionTestRepository, times(1)).save(any(QuestionsTests.class));

        // Assert that the result is not null
        assertNotNull(result);
        // Add more assertions here if needed
    }

}
