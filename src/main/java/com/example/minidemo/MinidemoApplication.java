package com.example.minidemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class MinidemoApplication {

	private static final Logger log = LoggerFactory.getLogger(MinidemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MinidemoApplication.class, args);
	}

	  @Bean
	  public CommandLineRunner demo(NamedCategoryRepository repository) {
	    return (args) -> {
	      // save a few customers
	      repository.save(new NamedCategory("Jack"));
	      repository.save(new NamedCategory("Chloe"));
	      repository.save(new NamedCategory("Kim"));
	      repository.save(new NamedCategory("David"));
	      repository.save(new NamedCategory("Michelle"));

	      // fetch all customers
	      log.info("NamedCategories found with findAll():");
	      log.info("-------------------------------");
	      for (NamedCategory category : repository.findAll()) {
	        log.info(category.toString());
	      }
	      log.info("");

	      // fetch an individual customer by ID
	      NamedCategory category = repository.findById(1L);
	      log.info("NamedCategory found with findById(1L):");
	      log.info("--------------------------------");
	      log.info(category.toString());
	      log.info("");

	      // fetch customers by last name
	      log.info("Customer found with findByName('David'):");
	      log.info("--------------------------------------------");
	      repository.findByName("David").forEach(david -> {
	        log.info(david.toString());
	      });
	      log.info("");
	    };
	  }
}
