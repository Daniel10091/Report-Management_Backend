package com.example.report.domain.service.auth;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.report.domain.dto.auth.AuthResponse;
import com.example.report.domain.dto.auth.UserAuthDTO;
import com.example.report.domain.exception.auth.UserAuthException;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserAuthService {
  
  private final String KEYCLOACK_URL = "${spring.security.oauth2.resourceserver.jwt.base-uri}";

  public UserAuthService() {
  }

  public AuthResponse userAuthentication(UserAuthDTO userAuthDTO) {
    RestTemplate restTemplate = new RestTemplate();

    try {
      var result = restTemplate.getForObject(KEYCLOACK_URL, AuthResponse.class);
      return result;
    } catch (Exception e) {
      System.out.println("--> { AUTH_ERROR }: " + e.getMessage());
      throw new UserAuthException("Error to authenticate user", e);
    }
  }

}
