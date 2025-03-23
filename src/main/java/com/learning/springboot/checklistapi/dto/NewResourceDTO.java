package com.learning.springboot.checklistapi.dto;

public class NewResourceDTO {
    private String guid;
    
	public NewResourceDTO() {
		
	}

	public NewResourceDTO(String guid) {
		super();
		this.guid = guid;
	}

	public String getGuid() {
		return guid;
	}
	
}
