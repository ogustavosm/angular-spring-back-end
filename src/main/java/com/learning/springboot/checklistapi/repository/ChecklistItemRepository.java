package com.learning.springboot.checklistapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.springboot.checklistapi.entity.ChecklistItemEntity;

@Repository
public interface ChecklistItemRepository extends JpaRepository<ChecklistItemEntity, Long> {

	Optional<ChecklistItemEntity> findByGuid(String guid);
	
	List<ChecklistItemEntity> findByCategoryGuid(String guid);
	
}
