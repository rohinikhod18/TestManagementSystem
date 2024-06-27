package com.bnt.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CategoryTest {

	//This is Positive Test Case
		@Test
		public void testCategoryModel() {

			Category category = new Category();
			category.setTitle("Spring Core");
			category.setDescription("This is Spring Core Category Created");

			assertEquals("Spring Core", category.getTitle());
			assertEquals("This is Spring Core Category Created", category.getDescription());

		}
		
		//This is Negative Test Cases
		@Test
		public void testCategoryModelWithNullDescription() {
		    try {
		        Category category = new Category();
		        category.setTitle("Spring Core");
		        category.setDescription(null);
		        
		    } catch (IllegalArgumentException e) {
		        return;
		    }
		    fail("Expected IllegalArgumentException, but no exception was thrown");
		}

		@Test
		public void testCategoryModelWithBlankDescription() {
		    try {
		        Category category = new Category();
		        category.setTitle("Test Category");
		        category.setDescription("   "); 
		    } catch (IllegalArgumentException e) {
		        return;
		    }
		    fail("Expected IllegalArgumentException, but no exception was thrown");
		}
}