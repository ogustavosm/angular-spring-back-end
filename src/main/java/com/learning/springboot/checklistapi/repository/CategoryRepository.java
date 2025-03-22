package com.learning.springboot.checklistapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.springboot.checklistapi.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
	
	Optional<CategoryEntity> findByGuid(String guid);

}
