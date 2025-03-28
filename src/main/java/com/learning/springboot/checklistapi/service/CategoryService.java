package com.learning.springboot.checklistapi.service;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.learning.springboot.checklistapi.entity.CategoryEntity;
import com.learning.springboot.checklistapi.entity.ChecklistItemEntity;
import com.learning.springboot.checklistapi.exception.CategoryServiceException;
import com.learning.springboot.checklistapi.exception.ResourceNotFoundException;
import com.learning.springboot.checklistapi.repository.CategoryRepository;
import com.learning.springboot.checklistapi.repository.ChecklistItemRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryService {

    private ChecklistItemRepository checklistItemRepository;
    private CategoryRepository categoryRepository;
    
    private static final Logger LOGGER = LogManager.getLogger(CategoryService.class);
    
    
    @Autowired
    public CategoryService(ChecklistItemRepository checklistItemRepository, CategoryRepository categoryRepository){
        this.checklistItemRepository = checklistItemRepository;
        this.categoryRepository = categoryRepository;
    }

    
    public CategoryEntity addNewCategory(String name){

        if(!StringUtils.hasText(name)){
            throw new IllegalArgumentException("Category name cannot be empty or null");
        }

        CategoryEntity newCategory = new CategoryEntity();
        newCategory.setGuid(UUID.randomUUID().toString());
        newCategory.setName(name);

        LOGGER.debug("Adding a new category with name [ name = {} ]", name);

        return this.categoryRepository.save(newCategory);
    }

    public CategoryEntity updateCategory(String guid, String name){
        if(!StringUtils.hasText(guid) || !StringUtils.hasText(name)){
            throw new IllegalArgumentException("Invalid parameters provided to update a category");
        }

        CategoryEntity retrievedCategory = this.categoryRepository.findByGuid(guid).orElseThrow(
                () -> new ResourceNotFoundException("Category not found.")
        );

        retrievedCategory.setName(name);
        LOGGER.debug("Updating category [ guid = {}, newName = {} ]", guid, name);

        return this.categoryRepository.save(retrievedCategory);
    }

    public void deleteCategory(String guid){
        if(!StringUtils.hasText(guid)){
            throw new IllegalArgumentException("Category guid cannot be empty or null");
        }
        CategoryEntity retrievedCategory = this.categoryRepository.findByGuid(guid).orElseThrow(
                () -> new ResourceNotFoundException("Category not found.")
        );

        List<ChecklistItemEntity> checklistItems = this.checklistItemRepository.findByCategoryGuid(guid);
        if(!CollectionUtils.isEmpty(checklistItems)){
            throw new CategoryServiceException("It is not possible to delete given category as it has been used by checklist items");
        }
        LOGGER.debug("Deleting category [ guid = {} ]",guid);
        this.categoryRepository.delete(retrievedCategory);
    }

    public Iterable<CategoryEntity> findAllCategories(){
        return this.categoryRepository.findAll();
    }

    public CategoryEntity findCategoryByGuid(String guid){
        if(!StringUtils.hasText(guid)){
            throw new IllegalArgumentException("Category guid cannot be empty or null");
        }
        return this.categoryRepository.findByGuid(guid).orElseThrow(
                () -> new ResourceNotFoundException("Category not found.")
        );
    }
}
