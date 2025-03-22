package com.learning.springboot.checklistapi.entity;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
	
	private String guid;
	

	public BaseEntity() {

	}

	public BaseEntity(String guid) {
		super();
		this.guid = guid;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	
	

}
