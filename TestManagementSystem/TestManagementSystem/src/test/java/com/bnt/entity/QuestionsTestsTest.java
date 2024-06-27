package com.bnt.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuestionsTestsTest {

	@Test
	public void testCreateQuestionsTestsWithValidData() {

		Category category = new Category();
		category.setCategoryId(1L);
		category.setTitle("Spring Core");
		category.setDescription("This is Spring Core Category Created");

		Tests test = new Tests();
		test.setTitle("Core Java Test");
		test.setDescription("Created Core Java Test");
		test.setMaxMarks(100);
		test.setNumberOfQuestions(20);
		test.setActive(true);

		Questions question = new Questions();
		question.setContent("What is the largest mammal in the india");
		question.setOption1("African Elephant");
		question.setOption2("Blue Whale");
		question.setOption3("Giraffe");
		question.setOption4("Polar Bear");
		question.setAnswer("Blue Whale");
		question.setMarks("100");
		question.setCategory(category);

		QuestionsTests questionsTests = new QuestionsTests();
		questionsTests.setTests(test);
		questionsTests.setQuestions(question);

		assertEquals(test, questionsTests.getTests());
		assertEquals(question, questionsTests.getQuestions());
	}
}
