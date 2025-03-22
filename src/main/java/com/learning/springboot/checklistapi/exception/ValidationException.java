package com.learning.springboot.checklistapi.exception;

public class ValidationException extends RuntimeException{

	private static final long serialVersionUID = 7331686415734436687L;

	public ValidationException(String message) {
		super(message);
	}

}
