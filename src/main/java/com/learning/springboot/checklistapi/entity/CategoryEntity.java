package com.learning.springboot.checklistapi.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name="Category")
@Table(indexes = {@Index(name = "IDX_GUID_CAT", columnList = "guid")})
public class CategoryEntity extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	
	@Column(unique = true)
	private String name;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<ChecklistItemEntity> checklistItems;

	
	public CategoryEntity() {
		super();
	}

	public CategoryEntity(Long categoryId, String name, List<ChecklistItemEntity> checklistItems) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.checklistItems = checklistItems;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ChecklistItemEntity> getChecklistItems() {
		return checklistItems;
	}

	public void setChecklistItems(List<ChecklistItemEntity> checklistItems) {
		this.checklistItems = checklistItems;
	}
	
	

}
