package com.example.minidemo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface NamedCategoryRepository extends CrudRepository<NamedCategory, Long> {

	  List<NamedCategory> findByName(String name);

	  NamedCategory findById(long id);
}
