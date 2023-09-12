package com.example.report.domain.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAuthDTO {
  
  @JsonProperty("grant_type")
  private String grandType;

  @JsonProperty("client_id")
  private String clientId;

  @JsonProperty("useraname")
  private String username;

  @JsonProperty("password")
  private String password;

}
