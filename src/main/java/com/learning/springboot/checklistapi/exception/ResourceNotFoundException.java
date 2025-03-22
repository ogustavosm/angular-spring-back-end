package com.learning.springboot.checklistapi.exception;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -9175771365226966475L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
