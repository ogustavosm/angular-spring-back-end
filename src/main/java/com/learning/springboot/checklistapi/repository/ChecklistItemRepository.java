package com.learning.springboot.checklistapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.springboot.checklistapi.entity.ChecklistItemEntity;

public interface ChecklistItemRepository extends JpaRepository<ChecklistItemEntity, Long> {

	Optional<ChecklistItemRepository> findByGuid(String guid);
	
}
