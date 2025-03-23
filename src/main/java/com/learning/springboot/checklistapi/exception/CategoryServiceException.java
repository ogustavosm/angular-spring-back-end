package com.learning.springboot.checklistapi.exception;

public class CategoryServiceException extends RuntimeException{

    private static final long serialVersionUID = 3911006538354090105L;

	public CategoryServiceException(String message){
        super(message);
    }
}
