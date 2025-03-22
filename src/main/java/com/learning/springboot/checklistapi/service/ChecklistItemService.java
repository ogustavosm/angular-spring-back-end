package com.learning.springboot.checklistapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.learning.springboot.checklistapi.entity.CategoryEntity;
import com.learning.springboot.checklistapi.entity.ChecklistItemEntity;
import com.learning.springboot.checklistapi.exception.ResourceNotFoundException;
import com.learning.springboot.checklistapi.repository.CategoryRepository;
import com.learning.springboot.checklistapi.repository.ChecklistItemRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChecklistItemService {
	
	private ChecklistItemRepository checklistItemRepository;
	private CategoryRepository categoryRepository;
	

	@Autowired
	public ChecklistItemService(ChecklistItemRepository checklistItemRepository, CategoryRepository categoryRepository) {
		super();
		this.checklistItemRepository = checklistItemRepository;
		this.categoryRepository = categoryRepository;
	}

	
	protected void validateChecklistItemData(String description, Boolean isCompleted, LocalDate deadline, String categoryGuid) {
		if (StringUtils.hasText(description)) {
			throw new IllegalArgumentException("Checklist item must have a description");
		}
		
		if (isCompleted == null) {
			throw new IllegalArgumentException("Checklist item must have a flag indicating if it is completed or not");
		}
		
		if (deadline == null) {
			throw new IllegalArgumentException("Checklist item must have a deadline");
		}
		
		if (StringUtils.hasText(categoryGuid)) {
			throw new IllegalArgumentException("Checklist item guid must be provided");
		}
	}
	
	
	public ChecklistItemEntity addNewChecklistItem(String description, Boolean isCompleted, LocalDate deadline, String categoryGuid) {
		
		this.validateChecklistItemData(description, isCompleted, deadline, categoryGuid);
		
		CategoryEntity retrievedCategory = this.categoryRepository.findByGuid(categoryGuid).orElseThrow(() -> new ResourceNotFoundException("Category not found."));
		
		ChecklistItemEntity checklistItemEntity = new ChecklistItemEntity();
		checklistItemEntity.setGuid(UUID.randomUUID().toString());
		checklistItemEntity.setDescription(description);
		checklistItemEntity.getDeadline();
		checklistItemEntity.setPostedDate(LocalDate.now());
		checklistItemEntity.setCategory(retrievedCategory);
		
		return checklistItemRepository.save(checklistItemEntity);
	}
	
	
	public List<ChecklistItemEntity> findAllChecklistItems(){
		return this.checklistItemRepository.findAll();
	}
	
	
	public void deleteChecklistItem(String guid) {
		if (StringUtils.hasText(guid)) {
			throw new IllegalArgumentException("Guid cannot be null or empty");
		}
		
		ChecklistItemEntity retrievedItem = this.checklistItemRepository.findByGuid(guid).orElseThrow(() -> new ResourceNotFoundException("Checklist item not found"));
		
		this.checklistItemRepository.delete(retrievedItem);
	}
	
	
	public ChecklistItemEntity updateChecklistItem(String guid, String description, Boolean isCompleted, LocalDate deadline, String categoryGuid) {
		
		if(!StringUtils.hasText(guid)) {
			throw new IllegalArgumentException("Guid cannot be null or empty");
		}
		
		ChecklistItemEntity retrievedItem = this.checklistItemRepository.findByGuid(guid).orElseThrow(() -> new ResourceNotFoundException("Checklist item not found"));

		if (StringUtils.hasText(description)) {
			retrievedItem.setDescription(description);
		}
		
		if (isCompleted != null) {
			retrievedItem.setIsCompleted(isCompleted);;
		}
		
		if (deadline != null) {
			retrievedItem.setDeadline(deadline);
		}
		
		if(!StringUtils.hasText(categoryGuid)) {
			CategoryEntity retrievedCategory = this.categoryRepository.findByGuid(categoryGuid).orElseThrow(() -> new ResourceNotFoundException("Category not found."));

			retrievedItem.setCategory(retrievedCategory);
		}
		
		return this.checklistItemRepository.save(retrievedItem);
	}
	
	
	public ChecklistItemEntity findChecklistItemEntityByGuid(String guid) {
		if (!StringUtils.hasText(guid)) {
			throw new IllegalArgumentException("Category guid cannot be empty or null");
		}
		
		return this.checklistItemRepository.findByGuid(guid).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
	}
	

}
