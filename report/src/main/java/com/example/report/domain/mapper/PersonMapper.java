package com.example.report.domain.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.report.domain.dto.UserDTO;
import com.example.report.domain.model.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {
  
  @Mapping(source = "person.id", target = "code")
  @Mapping(source = "person.user.userIdentifier", target = "userIdentifier")
  @Mapping(source = "person.user.online", target = "online")
  @Mapping(source = "person.user.active", target = "active")
  @Mapping(source = "person.user.avatar", target = "avatar")
  @Mapping(source = "person.user.themeImage", target = "themeImage")
  public UserDTO toDto(Person person);

  @InheritInverseConfiguration
  public Person toEntity(UserDTO userDTO);

}
