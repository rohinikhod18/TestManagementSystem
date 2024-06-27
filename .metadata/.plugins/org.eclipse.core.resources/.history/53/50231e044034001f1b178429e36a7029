package com.bnt.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.entity.Category;
import com.bnt.exception.CategoryNotFoundException;
import com.bnt.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/testmanagement/api/v1/categories")
public class CategoryController {

	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService service;

	@PostMapping
	public ResponseEntity<Category> addNewCategory(@Valid @RequestBody Category category) {
		return Optional.ofNullable(service.addNewCategory(category)).map(addCategory -> {
			logger.info("Added a new category: {}", addCategory);
			return ResponseEntity.ok(addCategory);
		}).orElseGet(() -> {
			logger.error("Failed to add a new category.");
			return ResponseEntity.badRequest().build();
		});
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/get")
	public List<Category> getAllCategory() {
		List<Category> showCategories = service.getAllCategory();
		logger.info("Getting all categories: {}", showCategories);
		showCategories.forEach(category -> logger.info("Category: {}", category));
		return showCategories;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{category_id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("category_id") Long categoryId) {
		return Optional.ofNullable(service.getCategoryById(categoryId)).map(category -> {
			logger.info("Getting category by ID: {}", category);
			return ResponseEntity.ok(category);
		}).orElseThrow(() -> {
			logger.error("Category not found with ID: {}", categoryId);
			return new CategoryNotFoundException("Category not found with ID: " + categoryId);
		});
	}

	@PutMapping
	public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
		return Optional.ofNullable(service.updateCategory(category))
				.map(updatedCategory -> ResponseEntity.ok(updatedCategory)).orElseThrow(() -> {
					logger.error("Error occurred while updating category");
					return new CategoryNotFoundException("Error occurred while updating category");
				});
	}

	@DeleteMapping("/{category_id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("category_id") Long categoryId) {
	    service.deleteCategory(categoryId);
	    logger.info("Deleting category with ID: {}", categoryId);
	    return ResponseEntity.ok().build();
	}
}
