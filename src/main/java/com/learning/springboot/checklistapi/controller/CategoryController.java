package com.learning.springboot.checklistapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springboot.checklistapi.dto.CategoryDTO;
import com.learning.springboot.checklistapi.entity.CategoryEntity;
import com.learning.springboot.checklistapi.exception.ValidationException;
import com.learning.springboot.checklistapi.service.CategoryService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1/api/categories")
public class CategoryController {

	private CategoryService categoryService;
	
	@Autowired
	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	
	@GetMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryDTO>> getAllCategories(){
		
		List<CategoryDTO> resp = this.categoryService.findAllCategories().stream()
				.map(categoryEntity -> CategoryDTO.toDTO(categoryEntity)).collect(Collectors.toList());
		
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	
	@PostMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewCategory(@RequestBody CategoryDTO categoryDTO){
		
		if(categoryDTO.getGuid() == null) {
			throw new ValidationException("Category canot be null");
		}
		
		CategoryEntity newCategory = this.categoryService.addNewCategory(
				categoryDTO.getName());
		
		return new ResponseEntity<>(newCategory.getGuid(), HttpStatus.CREATED);
	}
	
	
	@PutMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateCategory(@RequestBody CategoryDTO categoryDTO){
		
		if (StringUtils.hasText(categoryDTO.getGuid())) {
			throw new ValidationException("Category guid cannot be null or empty");
		}
		
		this.categoryService.updateCategory(
				categoryDTO.getGuid(), 
				categoryDTO.getName());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@DeleteMapping(value = "{guid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteCategory(@PathVariable String guid){
		this.categoryService.deleteCategory(guid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
