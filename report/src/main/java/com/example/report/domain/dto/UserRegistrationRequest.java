package com.example.report.domain.dto;

import java.time.LocalDate;

public record UserRegistrationRequest(
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
  
  public UserRegistrationRequest(UserRegistrationRequestDTO userDTO) {
    this(
      userDTO.getFirstName(),
      userDTO.getLastName(),
      userDTO.getUserIdentifier(),
      userDTO.getGender(),
      userDTO.getBirthDate(),
      userDTO.getITIN(),
      userDTO.getActive(),
      userDTO.getAvatar(),
      userDTO.getThemeImage()
    );
  }

}
