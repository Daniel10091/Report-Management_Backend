package com.example.report.domain.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.report.domain.dto.auth.UserAuthDTO;
import com.example.report.domain.service.auth.UserAuthService;

@RestController
@RequestMapping(value = "/api/v1")
public class UserAuthController {
  
  private final UserAuthService userAuthService;

  public UserAuthController(UserAuthService userAuthService) {
    this.userAuthService = userAuthService;
  }

  @PostMapping(value = "/auth")
  public ResponseEntity<?> userAuthentication(@RequestBody UserAuthDTO userAuthDTO) {
    var result = userAuthService.userAuthentication(userAuthDTO);
    return ResponseEntity.ok(result);
  }
  
}
