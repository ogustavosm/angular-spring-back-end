package com.learning.springboot.checklistapi.dto;

public class UpdateStatusDTO {
    public boolean isComplete;
    
    
	public UpdateStatusDTO() {
		super();
	}

	public UpdateStatusDTO(boolean isComplete) {
		super();
		this.isComplete = isComplete;
	}

	
	public boolean isComplete() {
		return isComplete;
	}

    
}
