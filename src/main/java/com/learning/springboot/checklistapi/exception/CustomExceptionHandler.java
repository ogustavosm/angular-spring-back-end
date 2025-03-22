package com.learning.springboot.checklistapi.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<Object> handleResourceNotFoundException(Exception ex, WebRequest request) throws Exception {
		// add logger
		return new ResponseEntity<>(new ExceptionalResponse(LocalDateTime.now(), ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY), 
				HttpStatus.UNPROCESSABLE_ENTITY);
	}

}
