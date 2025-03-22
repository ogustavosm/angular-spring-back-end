package com.learning.springboot.checklistapi.entity;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name="ChecklistItem")
@Table(indexes = {@Index(name = "IDX_GUID_CK_IT", columnList = "guid")})
public class ChecklistItemEntity extends BaseEntity  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long checkListItemId;
	
	private String description;
	
	private Boolean isCompleted;
	
	private LocalTime deadline;
	
	private LocalTime postedDate;
	
	@ManyToOne
	private CategoryEntity category;

}
