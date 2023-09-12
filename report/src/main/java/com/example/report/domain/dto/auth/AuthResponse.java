package com.example.report.domain.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthResponse {
  
  private String accessToken;
  private Integer expiresIn;
  private Integer refreshExpiresIn;
  private String refreshToken;
  private String tokenType;
  private Integer noBeforePolicy;
  private String sessionState;
  private String scope;

}
