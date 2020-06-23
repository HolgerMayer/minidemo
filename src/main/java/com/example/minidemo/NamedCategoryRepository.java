package com.example.minidemo;


import org.springframework.data.jpa.repository.JpaRepository;

public interface NamedCategoryRepository extends JpaRepository<NamedCategory, Long> {


}
