package com.example.report.domain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.report.domain.service.PersonService;

@RestController
@RequestMapping(value = "/api/v1/people")
public class PersonController {
  
  private PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @PostMapping(value = "/avatar")
  public ResponseEntity<?> saveUserAvatar(@RequestBody() byte[] avatarBytes) {
    return ResponseEntity.ok(personService.saveUserAvatar(avatarBytes));
  }
  
}
