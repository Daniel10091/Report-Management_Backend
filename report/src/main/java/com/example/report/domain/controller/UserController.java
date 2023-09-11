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
import com.example.report.domain.mapper.PersonMapper;
import com.example.report.domain.service.UserService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {
  
  private final UserService userService;
  private final PersonMapper personMapper;

  public UserController(UserService userService, PersonMapper personMapper) {
    this.userService = userService;
    this.personMapper = personMapper;
  }

  // Get all users
  @GetMapping(value = "/users")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<List<UserDTO>> getUsers() {
    var result = userService.getAllUsers().stream().map(personMapper::toDto).collect(Collectors.toList());
    return ResponseEntity.ok(result);
  }

  // Get all online users
  @GetMapping(value = "/users/filter")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<List<UserDTO>> getUsersByStatus(@PathParam(value = "option") String option) {
    var result = userService.getUsersByStatus(option).stream().map(personMapper::toDto).collect(Collectors.toList());
    return ResponseEntity.ok(result);
  }

  // Find a user by id
  @GetMapping(value = "/users/{id}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<UserDTO> findUser(@PathVariable(value = "id") Long id) {
    var result = userService.findUserById(id);
    return ResponseEntity.ok(personMapper.toDto(result));
  }


  // Register a new user
  @PostMapping(value = "/users")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO user) {
    var result = userService.registerUser(personMapper.toEntity(user));
    return ResponseEntity.ok(personMapper.toDto(result));
  }

  // Update a user
  @PutMapping(value = "/users/{id}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") Long id, @RequestBody UserDTO user) {
    var result = userService.updateUser(id, personMapper.toEntity(user));
    return ResponseEntity.ok(personMapper.toDto(result));
  }
  
  // Update a user active
  @PutMapping(value = "/users")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<UserDTO> updateUserActive(@PathParam(value = "id") Long id, @PathParam(value = "active") Boolean active) {
    var result = userService.updateUserActive(id, active);
    return ResponseEntity.ok(personMapper.toDto(result));
  }

  // Delete a user
  @DeleteMapping(value = "/users/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) throws Exception {
    userService.deleteUser(id);
    return ResponseEntity.ok().build();
  }

}
