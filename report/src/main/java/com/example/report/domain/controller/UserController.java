package com.example.report.domain.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.report.domain.dto.UserDTO;
import com.example.report.domain.exception.UserNotFoundException;
import com.example.report.domain.mapper.PersonMapper;
import com.example.report.domain.service.UserService;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {
  
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  // Get all users
  @GetMapping(value = "/users")
  @PreAuthorize("hasRole('admin')")
  public ResponseEntity<List<UserDTO>> getUsers() {
    var result = userService.getAllUsers().stream().map(PersonMapper::toDto).collect(Collectors.toList());
    return ResponseEntity.ok(result);
  }

  // Find a user by id
  @GetMapping(value = "/users/{id}")
  @PreAuthorize("hasRole('admin')")
  public ResponseEntity<?> findUser(@PathVariable(value = "id") Long id) {
    try {
      var result = userService.findUserById(id);
      return ResponseEntity.ok(PersonMapper.toDto(result));
    } catch (UserNotFoundException e) {
      System.out.println("[ ERROR ] -> Error to find user: " + e.getMessage());
      // return ResponseEntity.notFound().build();
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  // Register a new user
  @PostMapping(value = "/users")
  @PreAuthorize("hasRole('admin')")
  public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO personDTO) {
    var person = PersonMapper.toEntity(personDTO);
    var result = userService.registerUser(person);
    return ResponseEntity.ok(PersonMapper.toDto(result));
  }

  // Update a user
  @PutMapping(value = "/users/{id}")
  @PreAuthorize("hasRole('admin')")
  public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") Long id, @RequestBody UserDTO personDTO) {
    var person = PersonMapper.toEntity(personDTO);
    var result = userService.updateUser(id, person);
    return ResponseEntity.ok(PersonMapper.toDto(result));
  }

  // Delete a user
  @DeleteMapping(value = "/users/{id}")
  @PreAuthorize("hasRole('admin')")
  public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
    try {
      userService.deleteUser(id);
      return ResponseEntity.ok().build();
    } catch (UserNotFoundException e) {
      System.out.println("[ ERROR ] -> Error to delete user: " + e.getMessage());
      return ResponseEntity.notFound().build();
    }
  }

}
