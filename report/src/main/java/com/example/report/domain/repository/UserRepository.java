package com.example.report.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.report.domain.model.Person;

@Repository
public interface UserRepository extends JpaRepository<Person, Long> {
  
  Optional<Person> findById(Long id);

  @Query(" SELECT person FROM Person person " +
         " WHERE person.firstName LIKE '%:firstName%' " +
         " AND person.lastName LIKE '%:lastName%'")
  Optional<Person> findPersonByName(
    @Param("firstName") String firstName,
    @Param("lastName") String lastName
  );

  Optional<Person> findPersonByUserUserIdentifier(String userIdentifier);

  @Query(" SELECT person FROM Person person " +
         " LEFT JOIN person.user user " +
         " ON person.id = user.person.id " +
         " WHERE user.active = :active ")
  List<Person> findAllActiveUsers(Boolean active);

  @Query(" SELECT person FROM Person person " +
         " LEFT JOIN person.user user " +
         " ON person.id = user.person.id " +
         " WHERE user.online = :online ")
  List<Person> findAllOnlineUsers(boolean online);

}
