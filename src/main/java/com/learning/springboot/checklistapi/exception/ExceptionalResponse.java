package com.learning.springboot.checklistapi.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ExceptionalResponse {

    private LocalDateTime timestamp;
    private String message;
    private HttpStatus status;
    
    
	public ExceptionalResponse() {
	
	}

	public ExceptionalResponse(LocalDateTime timestamp, String message, HttpStatus status) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
	}

	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
    
	
}
