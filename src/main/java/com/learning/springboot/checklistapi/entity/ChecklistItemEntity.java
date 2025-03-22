package com.learning.springboot.checklistapi.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="ChecklistItem")
@Table(indexes = {@Index(name = "IDX_GUID_CK_IT", columnList = "guid")})
public class ChecklistItemEntity extends BaseEntity  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long checkListItemId;
	
	private String description;
	
	private Boolean isCompleted;
	
	private LocalDate deadline;
	
	private LocalDate postedDate;
	
	@ManyToOne
	private CategoryEntity category;

	
	public ChecklistItemEntity() {
		super();
	}

	public ChecklistItemEntity(Long checkListItemId, String description, Boolean isCompleted, LocalDate deadline,
			LocalDate postedDate, CategoryEntity category) {
		super();
		this.checkListItemId = checkListItemId;
		this.description = description;
		this.isCompleted = isCompleted;
		this.deadline = deadline;
		this.postedDate = postedDate;
		this.category = category;
	}

	public Long getCheckListItemId() {
		return checkListItemId;
	}

	public void setCheckListItemId(Long checkListItemId) {
		this.checkListItemId = checkListItemId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public LocalDate getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(LocalDate postedDate) {
		this.postedDate = postedDate;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}
	

}
