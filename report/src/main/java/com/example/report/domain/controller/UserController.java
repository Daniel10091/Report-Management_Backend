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
import com.example.report.domain.mapper.UserMapper;
import com.example.report.domain.service.UserService;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {
  
  private final UserService userService;
  private final UserMapper userMapper;

  public UserController(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }

  // Get all users
  @GetMapping(value = "/users")
  @PreAuthorize("hasRole('admin')")
  public ResponseEntity<List<UserDTO>> getUsers() {
    var result = userService.getAllUsers().stream().map(UserMapper::toDto).collect(Collectors.toList());
    return ResponseEntity.ok(result);
  }

  // Find a user by id
  @GetMapping(value = "/users/{id}")
  @PreAuthorize("hasRole('admin')")
  public ResponseEntity<UserDTO> findUser(@PathVariable(value = "id") Long id) {
    var result = userService.findUserById(id);
    return ResponseEntity.ok(UserMapper.toDto(result));
  }

  // Register a new user
  @PostMapping(value = "/users")
  @PreAuthorize("hasRole('admin')")
  public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO user) {
    var result = userService.registerUser(UserMapper.toEntity(user));
    return ResponseEntity.ok(UserMapper.toDto(result));
  }

  // Update a user
  @PutMapping(value = "/users/{id}")
  @PreAuthorize("hasRole('admin')")
  public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") Long id, @RequestBody UserDTO user) {
    var result = userService.updateUser(id, UserMapper.toEntity(user));
    return ResponseEntity.ok(UserMapper.toDto(result));
  }

  // Delete a user
  @DeleteMapping(value = "/users/{id}")
  @PreAuthorize("hasRole('admin')")
  public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) throws Exception {
    userService.deleteUser(id);
    return ResponseEntity.ok().build();
  }

}
