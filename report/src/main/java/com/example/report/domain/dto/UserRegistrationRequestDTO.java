package com.example.report.domain.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegistrationRequestDTO {
  
  private String firstName;
  private String lastName;
  private String userIdentifier;
  private String gender;
  private LocalDate birthDate;
  private String ITIN;
  private Boolean active;
  private byte[] avatar;
  private byte[] themeImage;

}
