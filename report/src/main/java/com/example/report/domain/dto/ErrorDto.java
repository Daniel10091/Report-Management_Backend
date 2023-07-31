package com.example.report.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public final class ErrorDto {
  
  private int status;
  private String title;
  private String detail;
  private String instance;

}
