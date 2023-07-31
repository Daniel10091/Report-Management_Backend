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
import com.example.report.domain.dto.UserRegistrationRequest;
import com.example.report.domain.exception.UserNotFoundException;
import com.example.report.domain.mapper.UserDTOMapper;
import com.example.report.domain.mapper.UserMapper;
import com.example.report.domain.model.Person;
import com.example.report.domain.service.UserService;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {
  
  private final UserService userService;
  private final UserDTOMapper userDTOMapper;

  public UserController(UserService userService, UserDTOMapper userDTOMapper) {
    this.userService = userService;
    this.userDTOMapper = userDTOMapper;
  }

  // Get all users
  @GetMapping(value = "/users")
  @PreAuthorize("hasRole('admin')")
  public ResponseEntity<List<UserDTO>> getUsers() {
    var result = userService.getAllUsers().stream().map(userDTOMapper).collect(Collectors.toList());
    return ResponseEntity.ok(result);
  }

  // Find a user by id
  @GetMapping(value = "/users/{id}")
  @PreAuthorize("hasRole('admin')")
  public ResponseEntity<UserDTO> findUser(@PathVariable(value = "id") Long id) {
    var result = userService.findUserById(id);
    return ResponseEntity.ok(userDTOMapper.apply(result));
  }

  // Register a new user
  @PostMapping(value = "/users")
  @PreAuthorize("hasRole('admin')")
  public ResponseEntity<UserDTO> registerUser(@RequestBody UserRegistrationRequest user) {
    var result = userService.registerUser(user);
    return ResponseEntity.ok(userDTOMapper.apply(result));
  }

  // Update a user
  @PutMapping(value = "/users/{id}")
  @PreAuthorize("hasRole('admin')")
  public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") Long id, @RequestBody UserRegistrationRequest user) {
    var result = userService.updateUser(id, user);
    return ResponseEntity.ok(userDTOMapper.apply(result));
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
