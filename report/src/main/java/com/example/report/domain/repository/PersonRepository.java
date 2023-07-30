package com.example.report.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.report.domain.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
  
  Optional<Person> findById(Long id);

  @Query(" SELECT person FROM Person person " +
         " WHERE person.firstName LIKE '%:firstName%' " +
         " AND person.lastName LIKE '%:lastName%'")
  Optional<Person> findPersonByName(
    @Param("firstName") String firstName,
    @Param("lastName") String lastName
  );

  Optional<Person> findPersonByFirstNameAndLastName(String firstName, String lastName);

}
