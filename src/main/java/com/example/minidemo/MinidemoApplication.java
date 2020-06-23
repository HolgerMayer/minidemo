package com.example.minidemo;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class MinidemoApplication {

	private static final Logger log = LoggerFactory.getLogger(MinidemoApplication.class);
	 
	@Autowired
	private NamedCategoryService categoryService;
	 
	
	public static void main(String[] args) {
		SpringApplication.run(MinidemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(NamedCategoryRepository repository) {
		  
		  
	    return (args) -> {
	      // save a few customers^^
	    categoryService.saveNamedCategory(new NamedCategory("Jack"));
	    categoryService.saveNamedCategory(new NamedCategory("Chloe"));
	    categoryService.saveNamedCategory(new NamedCategory("Kim"));
	    categoryService.saveNamedCategory(new NamedCategory("David"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle1"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle2"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle3"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle4"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle5"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle6"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle7"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle8"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle9"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle10"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle11"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle12"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle13"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle14"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle15"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle16"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle17"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle18"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle19"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle20"));
	    categoryService.saveNamedCategory(new NamedCategory("Michelle21"));

	      // fetch all customers
	      log.info("NamedCategories found with findAll():");
	      log.info("-------------------------------");
	      for (NamedCategory category : categoryService.getAllNamedCategories()) {
	        log.info(category.toString());
	      }
	      log.info("");

	      // fetch an individual customer by ID
	      NamedCategory category = categoryService.getNamedCategoryById(1L);
	      log.info("NamedCategory found with getNamedCategoryById(1L):");
	      log.info("--------------------------------");
	      log.info(category.toString());
	      log.info("");

	      /* TODO
	      // fetch customers by last name
	      log.info("Customer found with findByName('David'):");
	      log.info("--------------------------------------------");
	      repository.findByName("David").forEach(david -> {
	        log.info(david.toString());
	      });
	      log.info("");
	      */
	    };
	  }
}
