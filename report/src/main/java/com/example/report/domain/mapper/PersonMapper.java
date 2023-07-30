package com.example.report.domain.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.report.domain.dto.PersonDTO;
import com.example.report.domain.model.Person;

@Mapper
public class PersonMapper {
  
  @Mapping(source = "person.id", target = "code")
  @Mapping(source = "person.user.userIdentifier", target = "userIdentifier")
  @Mapping(source = "person.user.avatarPath", target = "avatarPath")
  @Mapping(source = "person.user.themeImagePath", target = "themeImagePath")
  @Mapping(source = "person.user.active", target = "active")
  static PersonDTO toDto(Person Person) {
    throw new UnsupportedOperationException("Unimplemented method 'toDto'");
  }

  @InheritInverseConfiguration
  static Person toEntity(PersonDTO PersonDTO) {
    throw new UnsupportedOperationException("Unimplemented method 'toEntity'");
  }

}
