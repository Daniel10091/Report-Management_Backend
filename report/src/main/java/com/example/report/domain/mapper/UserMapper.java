package com.example.report.domain.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.example.report.domain.dto.UserDTO;
import com.example.report.domain.model.Person;

@Service
public class UserMapper implements Function<UserDTO, Person> {

  @Override
  public Person apply(UserDTO dto) {
    return new Person(
      dto.code(), 
      dto.firstName(), 
      dto.lastName(), 
      dto.birthDate(), 
      dto.gender(), 
      dto.ITIN(), 
      null, 
      null, 
      null
    );
  }

}
