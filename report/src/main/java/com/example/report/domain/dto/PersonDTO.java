package com.example.report.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PersonDTO {
  
  private Long code;
  private String firstName;
  private String lastName;
  private String userIdentifier;
  private byte[] avatarPath;
  private byte[] themeImagePath;
  private String gender;
  private LocalDate birthDate;
  private String ITIN;
  private Boolean active;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;

}
