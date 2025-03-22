package com.learning.springboot.checklistapi.exception;

public class CategoryServiceException extends RuntimeException{
	
 static final long serialVersionUID = 8622197126404368614L;

	public CategoryServiceException(String message) {
		super(message);
	}

}
