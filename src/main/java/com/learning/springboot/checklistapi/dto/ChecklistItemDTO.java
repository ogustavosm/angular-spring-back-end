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

    private CategoryDTO category;
    
    
    public ChecklistItemDTO() {
    	
	}

	public ChecklistItemDTO(
    		String guid,
			String description,
			Boolean isCompleted,
			LocalDate deadline, LocalDate postedDate,
			CategoryDTO category) {
		super();
		this.guid = guid;
		this.description = description;
		this.isCompleted = isCompleted;
		this.deadline = deadline;
		this.postedDate = postedDate;
		this.category = category;
	}

	private ChecklistItemDTO(Builder builder) {
        this.guid = builder.guid;
        this.description = builder.description;
        this.isCompleted = builder.isCompleted;
        this.deadline = builder.deadline;
        this.postedDate = builder.postedDate;
        this.category = builder.category;
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

    public CategoryDTO getCategory() {
        return category;
    }

    public static ChecklistItemDTO toDTO(ChecklistItemEntity checklistItemEntity) {
        return new Builder()
                .guid(checklistItemEntity.getGuid())
                .description(checklistItemEntity.getDescription())
                .deadline(checklistItemEntity.getDeadline())
                .postedDate(checklistItemEntity.getPostedDate())
                .isCompleted(checklistItemEntity.getIsCompleted())
                .category(checklistItemEntity.getCategory() != null ?
                        new CategoryDTO.Builder()
                                .guid(checklistItemEntity.getCategory().getGuid())
                                .name(checklistItemEntity.getCategory().getName())
                                .build() :
                        null)
                .build();
    }

    public static class Builder {
        private String guid;
        private String description;
        private Boolean isCompleted;
        private LocalDate deadline;
        private LocalDate postedDate;
        private CategoryDTO category;

        public Builder guid(String guid) {
            this.guid = guid;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder isCompleted(Boolean isCompleted) {
            this.isCompleted = isCompleted;
            return this;
        }

        public Builder deadline(LocalDate deadline) {
            this.deadline = deadline;
            return this;
        }

        public Builder postedDate(LocalDate postedDate) {
            this.postedDate = postedDate;
            return this;
        }

        public Builder category(CategoryDTO category) {
            this.category = category;
            return this;
        }

        public ChecklistItemDTO build() {
            return new ChecklistItemDTO(this);
        }
    }
}
