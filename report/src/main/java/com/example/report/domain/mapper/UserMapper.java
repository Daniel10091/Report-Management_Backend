package com.example.report.domain.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.report.domain.dto.UserDTO;
import com.example.report.domain.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(source = "user.id", target = "code")
  @Mapping(source = "user.person.firstName", target = "firstName")
  @Mapping(source = "user.person.lastName", target = "lastName")
  @Mapping(source = "user.person.birthDate", target = "birthDate")
  @Mapping(source = "user.person.gender", target = "gender")
  @Mapping(source = "user.person.itin", target = "itin")
  public UserDTO toDto(User user);

  @InheritInverseConfiguration
  public User toEntity(UserDTO PersonDTO);

}
