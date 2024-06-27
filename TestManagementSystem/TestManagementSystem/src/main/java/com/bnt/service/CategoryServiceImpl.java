package com.bnt.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.bnt.entity.Category;
import com.bnt.exception.CategoryNotFoundException;
import com.bnt.exception.DuplicateCategoryException;
import com.bnt.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository repository;

	public CategoryServiceImpl(CategoryRepository repository) {
		this.repository = repository;
	}

	@Override
	public Category addNewCategory(Category category) {
		repository.findByTitle(category.getTitle()).ifPresent(existingCategory -> {
			throw new DuplicateCategoryException("Category with title '" + category.getTitle() + "' already exists");
		});

		return repository.save(new Category(category.getTitle(), category.getDescription()));
	}
	
	@Override
	public List<Category> getAllCategory() {
	    return StreamSupport.stream(repository.findAll().spliterator(), false)
	            .collect(Collectors.toList());
	}

	public Category getCategoryById(Long categoryId) {
		return repository.findById(categoryId)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found" + categoryId));
	}

	@Override
	public Category updateCategory(Category category) {
	    return repository.findById(category.getCategoryId())
	            .map(existingCategory -> {
	                existingCategory.setTitle(category.getTitle());
	                existingCategory.setDescription(category.getDescription());
	                return repository.save(existingCategory);
	            })
	            .orElseThrow(() -> new CategoryNotFoundException("Failed to update category " + category.getCategoryId() + ", not found"));
	}

	@Override
	public void deleteCategory(Long categoryId) {
	    if (repository.existsById(categoryId)) {
	        repository.deleteById(categoryId);
	    } else {
	        throw new CategoryNotFoundException("Category with ID " + categoryId + " not found");
	    }
	}
}
