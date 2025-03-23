package com.learning.springboot.checklistapi.dto;

import javax.validation.constraints.NotBlank;

import com.learning.springboot.checklistapi.entity.CategoryEntity;

public class CategoryDTO {

    private String guid;

    @NotBlank(message = "Category name cannot be either null or empty")
    private String name;
    

    public CategoryDTO() {

	}
         
    public CategoryDTO(String guid, String name) {
		super();
		this.guid = guid;
		this.name = name;
	}

	private CategoryDTO(Builder builder) {
        this.guid = builder.guid;
        this.name = builder.name;
    }

	
    public String getGuid() {
        return guid;
    }

    public String getName() {
        return name;
    }

    public static CategoryDTO toDTO(CategoryEntity categoryEntity) {
        return new Builder()
                .guid(categoryEntity.getGuid())
                .name(categoryEntity.getName())
                .build();
    }

    public static class Builder {
        private String guid;
        private String name;

        public Builder guid(String guid) {
            this.guid = guid;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public CategoryDTO build() {
            return new CategoryDTO(this);
        }
    }
}
