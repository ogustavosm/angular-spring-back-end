package com.learning.springboot.checklistapi.dto;

import com.learning.springboot.checklistapi.entity.CategoryEntity;

import jakarta.validation.constraints.NotBlank;

public class CategoryDTO {
	
    private String guid;
    
    @NotBlank(message = "Category name cannot be either null or empty")
    private String name;
    
	public static CategoryDTO toDTO(CategoryEntity categoryEntity) {
		return new Builder()
                .guid(categoryEntity.getGuid())
                .name(categoryEntity.getName())
                .build();
	}

	public static class Builder {
	        
		 private final CategoryDTO categoryDTO;
		 
	        public Builder() {
	            this.categoryDTO = new CategoryDTO();
	        }

	        public Builder guid(String guid) {
	            this.categoryDTO.guid = guid;
	            return this;
	        }
	        
	        public Builder name(String name) {
	            this.categoryDTO.name = name;
	            return this;
	        }

	        public CategoryDTO build() {
	            return this.categoryDTO;
	        }
	    }

	
	public String getGuid() {
		return guid;
	}
	
	public String getName() {
		return name;
	}


}
