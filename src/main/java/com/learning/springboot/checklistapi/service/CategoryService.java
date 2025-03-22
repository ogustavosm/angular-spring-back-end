package com.learning.springboot.checklistapi.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.learning.springboot.checklistapi.entity.CategoryEntity;
import com.learning.springboot.checklistapi.entity.ChecklistItemEntity;
import com.learning.springboot.checklistapi.exception.CategoryServiceException;
import com.learning.springboot.checklistapi.exception.ResourceNotFoundException;
import com.learning.springboot.checklistapi.repository.CategoryRepository;
import com.learning.springboot.checklistapi.repository.ChecklistItemRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService {
	
	private ChecklistItemRepository checklistItemRepository;
	private CategoryRepository categoryRepository;
	
	
	@Autowired
	public CategoryService(ChecklistItemRepository checklistItemRepository, CategoryRepository categoryRepository) {
		super();
		this.checklistItemRepository = checklistItemRepository;
		this.categoryRepository = categoryRepository;
	}
	
	
	public CategoryEntity addNewCategory(String name) {
		if(!StringUtils.hasText(name)) {
			throw new IllegalArgumentException("Category name cannot be empty or null");
		}
		
		CategoryEntity newCategory = new CategoryEntity();
		newCategory.setGuid(UUID.randomUUID().toString());
		newCategory.setName(name);
		
		return this.categoryRepository.save(newCategory);
	}
	
	
	public CategoryEntity updateCategory(String guid, String name) {
		if (guid == null || !StringUtils.hasText(name)) {
			throw new IllegalArgumentException("Invalid parameter provided to update a category");
		}
		
		CategoryEntity retrieveCategory = this.categoryRepository.findByGuid(guid).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
				
		retrieveCategory.setName(name);
		
		return this.categoryRepository.save(retrieveCategory);
	}
	
	
	public void deleteCategory(String guid) {
		if (!StringUtils.hasText(guid)) {
			throw new IllegalArgumentException("Category guid cannot be empty or null");
		}
		
		CategoryEntity retrieveCategory = this.categoryRepository.findByGuid(guid).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		
		List<ChecklistItemEntity> checklistItems = this.checklistItemRepository.findByCategoryGuid(guid);
		
		if (!checklistItems.isEmpty()) {
			throw new CategoryServiceException("It is not possible to delete given category as it has been used by checklist items");
		}
		
		this.categoryRepository.delete(retrieveCategory);
	}
	
	
	public CategoryEntity findCategoryByGuid(String guid) {
		if (!StringUtils.hasText(guid)) {
			throw new IllegalArgumentException("Category guid cannot be empty or null");
		}
		
		return this.categoryRepository.findByGuid(guid).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
	}
	
	
	public List<CategoryEntity> findAllCategories() {
		return this.categoryRepository.findAll();
	}
	
	

}
