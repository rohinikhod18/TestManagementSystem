package com.bnt.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.bnt.entity.Category;
import com.bnt.exception.CategoryNotFoundException;
import com.bnt.repository.CategoryRepository;

@SpringBootTest
class CategoryServiceImplTest {

	@Mock
	private CategoryRepository categoryRepository;

	@InjectMocks
	private CategoryServiceImpl categoryService;

	public Category setCategoryRequest() {
		Category category = new Category();
		category.setCategoryId(1L);
		category.setTitle("Spring Core");
		category.setDescription("This is Spring Core Category Created");

		return category;

	}

	@Test
	public void testAddNewCategory() {

		Category inputCategory = setCategoryRequest();
		when(categoryRepository.save(any())).thenReturn(inputCategory);
		Category result = categoryService.addNewCategory(inputCategory);
		assertEquals(inputCategory, result);
	}

	@Test
	public void testGetAllCategory() {

		Category inputCategory = setCategoryRequest();
		List<Category> categories = new ArrayList<>();
		categories.add(inputCategory);
		when(categoryRepository.findAll()).thenReturn(categories);
		List<Category> result = categoryService.getAllCategory();
		assertEquals(categories.size(), result.size());
	}

	@Test
	public void testGetCategoryById() {
		Long categoryId = 1L;
		Category category = setCategoryRequest();
		when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
		Category result = categoryService.getCategoryById(categoryId);
		assertEquals(categoryId, result.getCategoryId());
		assertEquals("Spring Core", result.getTitle());
		assertEquals("This is Spring Core Category Created", result.getDescription());
	}

	@Test
	public void testGetCategoryById_NotFound() {
		Long categoryId = 1L;
		when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
		assertThrows(CategoryNotFoundException.class, () -> {
			categoryService.getCategoryById(categoryId);
		});
	}

	@Test
	public void testUpdateCategory() {

		Long categoryId = 1L;
		Category inputCategory = new Category();
		inputCategory.setCategoryId(categoryId);
		inputCategory.setTitle("UpdatedTitle");
		inputCategory.setDescription("UpdatedDescription");
		Category existingCategory = setCategoryRequest();
		when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));
		when(categoryRepository.save(any())).thenReturn(inputCategory);
		Category result = categoryService.updateCategory(inputCategory);

		assertEquals(categoryId, result.getCategoryId());
		assertEquals(inputCategory.getTitle(), result.getTitle());
		assertEquals(inputCategory.getDescription(), result.getDescription());
	}

	@Test
	public void testUpdateCategory_NotFound() {
		Long categoryId = 1L;
		Category inputCategory = new Category();
		when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
		assertThrows(CategoryNotFoundException.class, () -> {
			categoryService.updateCategory(inputCategory);
		});
	}

	@Test
	public void testDeleteCategory() {

		Long categoryId = 1L;
		when(categoryRepository.existsById(categoryId)).thenReturn(true);
		categoryService.deleteCategory(categoryId);

	}

	@Test
	public void testDeleteCategory_NotFound() {
		Long categoryId = 1L;
		when(categoryRepository.existsById(categoryId)).thenReturn(false);
		assertThrows(CategoryNotFoundException.class, () -> {
			categoryService.deleteCategory(categoryId);
		});
	}
}
