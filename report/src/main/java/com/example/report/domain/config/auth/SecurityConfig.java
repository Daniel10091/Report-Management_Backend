package com.example.report.domain.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
  
  private final JwtAuthConverter jwtAuthConverter;
  
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf()
        .disable()
      .authorizeHttpRequests()
        .anyRequest()
          .authenticated();

    http
      .oauth2ResourceServer()
        .jwt()
          .jwtAuthenticationConverter(jwtAuthConverter);
    
    http
      .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
    return http.build();
  }

}
