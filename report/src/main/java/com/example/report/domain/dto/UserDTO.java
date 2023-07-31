package com.example.report.domain.dto;

import java.time.LocalDate;

import com.example.report.domain.model.Person;

public record UserDTO(
  Long code,
  String firstName,
  String lastName,
  String userIdentifier,
  String gender,
  LocalDate birthDate,
  String ITIN,
  Boolean active,
  byte[] avatar,
  byte[] themeImage
) {
  
  public UserDTO(Person person) {
    this(
      person.getId(),
      person.getFirstName(),
      person.getLastName(),
      person.getUser().getUserIdentifier(),
      person.getGender(),
      person.getBirthDate(),
      person.getITIN(),
      person.getUser().getActive(),
      person.getUser().getAvatar(),
      person.getUser().getThemeImage()
    );
  }

}
