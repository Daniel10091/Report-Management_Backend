package com.example.report.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.report.domain.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
  
}
