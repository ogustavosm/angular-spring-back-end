package com.learning.springboot.checklistapi.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springboot.checklistapi.dto.ChecklistItemDTO;
import com.learning.springboot.checklistapi.dto.NewResourceDTO;
import com.learning.springboot.checklistapi.dto.UpdateStatusDTO;
import com.learning.springboot.checklistapi.entity.ChecklistItemEntity;
import com.learning.springboot.checklistapi.service.ChecklistItemService;

import jakarta.validation.ValidationException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1/api/checklist-items")
public class ChecklistItemController {

    private ChecklistItemService checklistItemService;

    public ChecklistItemController(ChecklistItemService checklistItemService) {
        this.checklistItemService = checklistItemService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ChecklistItemDTO>> getAllChecklistItems() {

        List<ChecklistItemDTO> resp = StreamSupport.stream(this.checklistItemService.findAllChecklistItems().spliterator(), false)
                .map(checklistItemEntity -> ChecklistItemDTO.toDTO(checklistItemEntity))
                .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NewResourceDTO> createNewChecklistItem(@RequestBody ChecklistItemDTO checklistItemDTO) {

        if(checklistItemDTO.getCategory() == null) {
            throw new ValidationException("Category cannot be null");
        }

        ChecklistItemEntity createdChecklistItem = this.checklistItemService.addNewChecklistItem(checklistItemDTO.getDescription(),
                checklistItemDTO.getIsCompleted(), checklistItemDTO.getDeadline(), checklistItemDTO.getCategory().getGuid());

        return new ResponseEntity<>(new NewResourceDTO(createdChecklistItem.getGuid()), HttpStatus.CREATED);
    }

    
    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateChecklistItem(@RequestBody ChecklistItemDTO checklistItemDTO) {

        if(!StringUtils.hasText(checklistItemDTO.getGuid())){
            throw new ValidationException("Checklist item guid cannot be null or empty");
        }
        this.checklistItemService.updateChecklistItem(checklistItemDTO.getGuid(),
                checklistItemDTO.getDescription(), checklistItemDTO.getIsCompleted(),
                checklistItemDTO.getDeadline(), checklistItemDTO.getCategory() != null ?
                        checklistItemDTO.getCategory().getGuid() : null);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
    @DeleteMapping(value="{guid}")
    public ResponseEntity<Void> deleteChecklistItem(@PathVariable String guid){
        this.checklistItemService.deleteChecklistItem(guid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
    @PatchMapping(value="{guid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCompletedStatus(@PathVariable String guid, @RequestBody UpdateStatusDTO status){
        this.checklistItemService.updateIsCompleteStatus(guid, status.isComplete);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
