package com.example.report.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PersonDTO {
  
  private Long code;
  private String firstName;
  private String lastName;
  private String avatarPath;
  private String themeImagePath;
  private LocalDate birthDate;
  private String gender;
  private String ITIN;
  private Long emailId;
  private Long addressId;
  private Long phoneId;
  private Boolean active;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;

}