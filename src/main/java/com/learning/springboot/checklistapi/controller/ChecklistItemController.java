package com.learning.springboot.checklistapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springboot.checklistapi.dto.ChecklistItemDTO;
import com.learning.springboot.checklistapi.entity.ChecklistItemEntity;
import com.learning.springboot.checklistapi.exception.ValidationException;
import com.learning.springboot.checklistapi.service.ChecklistItemService;


@RestController
@RequestMapping("/v1/api/checklist-items")
public class ChecklistItemController {
	
	private ChecklistItemService checklistItemService;

	
	@Autowired
	public ChecklistItemController(ChecklistItemService checklistItemService) {
		super();
		this.checklistItemService = checklistItemService;
	}
	
	
	@GetMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ChecklistItemDTO>> getAllChecklistItems(){
		
		List<ChecklistItemDTO> resp = this.checklistItemService.findAllChecklistItems().stream()
				.map(checklistItemEntity -> ChecklistItemDTO.toDTO(checklistItemEntity)).collect(Collectors.toList());
		
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	
	@PostMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewChecklistItem(@RequestBody ChecklistItemDTO checklistItemDTO){
		
		if(checklistItemDTO.getCategoryGuid() == null) {
			throw new ValidationException("Category canot be null");
		}
		
		ChecklistItemEntity newChecklistItem = this.checklistItemService.addNewChecklistItem(
				checklistItemDTO.getDescription(), 
				checklistItemDTO.getIsCompleted(), 
				checklistItemDTO.getDeadline(), 
				checklistItemDTO.getCategoryGuid());
		
		return new ResponseEntity<>(newChecklistItem.getGuid(), HttpStatus.CREATED);
	}
	
	
	@PutMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateChecklistItem(@RequestBody ChecklistItemDTO checklistItemDTO){
		
		if (StringUtils.hasLength(checklistItemDTO.getGuid())) {
			throw new ValidationException("Guid is mandatory");
		}
		
		this.checklistItemService.updateChecklistItem(
				checklistItemDTO.getGuid(), 
				checklistItemDTO.getDescription(), 
				checklistItemDTO.getIsCompleted(), 
				checklistItemDTO.getDeadline(), 
				checklistItemDTO.getCategoryGuid());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@DeleteMapping(value = "{guid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteChecklistItem(@PathVariable String guid){
		this.checklistItemService.deleteChecklistItem(guid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
