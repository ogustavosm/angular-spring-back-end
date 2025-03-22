package com.learning.springboot.checklistapi.dto;

import java.time.LocalDate;

import com.learning.springboot.checklistapi.entity.ChecklistItemEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ChecklistItemDTO {
	
	private String guid;
	
    @NotBlank(message = "Checklist item description cannot be either null or empty")
	private String description;
    
    @NotNull(message = "Is completed is mandatory")
	private Boolean isCompleted;
    
    @NotNull(message = "Deadline is mandatory")
	private LocalDate deadline;
    
	private LocalDate postedDate;
	
    @NotBlank(message = "Category guid cannot be either null or empty")
	private String categoryGuid;

	public static ChecklistItemDTO toDTO(ChecklistItemEntity checklistItemEntity) {
		return new Builder()
                .guid(checklistItemEntity.getGuid())
                .description(checklistItemEntity.getDescription())
                .isCompleted(checklistItemEntity.getIsCompleted())
                .deadline(checklistItemEntity.getDeadline())
                .postedDate(checklistItemEntity.getPostedDate())
                .categoryGuid((checklistItemEntity.getCategory() != null) ? checklistItemEntity.getCategory().getGuid() : null)
                .build();
	}
	
	 public static class Builder {
	        
		 private final ChecklistItemDTO checklistItemDTO;

	        public Builder() {
	            this.checklistItemDTO = new ChecklistItemDTO();
	        }

	        public Builder guid(String guid) {
	            this.checklistItemDTO.guid = guid;
	            return this;
	        }

	        public Builder description(String description) {
	            this.checklistItemDTO.description = description;
	            return this;
	        }

	        public Builder isCompleted(Boolean isCompleted) {
	            this.checklistItemDTO.isCompleted = isCompleted;
	            return this;
	        }

	        public Builder deadline(LocalDate deadline) {
	            this.checklistItemDTO.deadline = deadline;
	            return this;
	        }

	        public Builder postedDate(LocalDate postDate) {
	            this.checklistItemDTO.postedDate = postDate;
	            return this;
	        }

	        public Builder categoryGuid(String categoryGuid) {
	            this.checklistItemDTO.categoryGuid = categoryGuid;
	            return this;
	        }

	        public ChecklistItemDTO build() {
	            return this.checklistItemDTO;
	        }
	    }

	public String getGuid() {
		return guid;
	}

	public String getDescription() {
		return description;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public LocalDate getPostedDate() {
		return postedDate;
	}

	public String getCategoryGuid() {
		return categoryGuid;
	}
	 

}
