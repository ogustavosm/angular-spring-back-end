package com.learning.springboot.checklistapi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="Category")
@Table(indexes = { @Index(name = "IDX_GUID_CAT", columnList = "guid")})
public class CategoryEntity extends BaseEntity{

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
