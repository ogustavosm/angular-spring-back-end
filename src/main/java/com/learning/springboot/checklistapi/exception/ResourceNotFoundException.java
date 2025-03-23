package com.learning.springboot.checklistapi.exception;

public class ResourceNotFoundException extends RuntimeException{
	
    private static final long serialVersionUID = -3474743603106213965L;

	public ResourceNotFoundException(String message){
        super(message);
    }
}
