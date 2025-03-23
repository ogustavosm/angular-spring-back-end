package com.learning.springboot.checklistapi.dataloader;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.learning.springboot.checklistapi.entity.CategoryEntity;
import com.learning.springboot.checklistapi.repository.CategoryRepository;
import com.learning.springboot.checklistapi.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Profile("data-load")
@Slf4j
@Component
public class DbLoader implements CommandLineRunner {
	
    private static final Logger LOGGER = LogManager.getLogger(DbLoader.class);

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @Override
    public void run(String... args) throws Exception {

    	LOGGER.info("Populating db with categories");

        List<String> categoryNames = Arrays.asList(
                "Trabalho","Saúde","Educação","Pessoal", "Outros"
        );

        for(String categoryName : categoryNames){

            Optional<CategoryEntity> catOpt =
                    this.categoryRepository.findByName(categoryName);

            if(!catOpt.isPresent()){
                categoryService.addNewCategory(categoryName);
            }
        }

    }
}
