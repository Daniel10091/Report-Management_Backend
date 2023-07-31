package com.example.report.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.report.domain.dto.UserRegistrationRequest;
import com.example.report.domain.exception.UserAlreadyExistException;
import com.example.report.domain.exception.UserNotFoundException;
import com.example.report.domain.model.Person;
import com.example.report.domain.model.User;
import com.example.report.domain.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
  
  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Get all people
   * 
   * @return
   */
  public List<Person> getAllUsers() {
    return userRepository.findAll();
  }

  /**
   * Find a person by id
   * 
   * @param id
   * @return
   */
  public Person findUserById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
  }

  /**
   * Register a new person
   * 
   * @param person
   * @return
   */
  public Person registerUser(UserRegistrationRequest user) {
    Person newPerson = new Person();
    Person personExist = null;
    Person personReturn = null;

    // personExist = userRepository.findPersonByFirstNameAndLastName(
    //     user.firstName(),
    //     user.lastName()
    //   ).get();

    // if (personExist != null) {
    //   throw new UserAlreadyExistException("User with name '" + user.firstName() + " " + user.lastName() + "' already exists");
    // } else {

    // }
    newPerson.setFirstName(user.firstName());
    newPerson.setLastName(user.lastName());
    newPerson.setBirthDate(user.birthDate());
    newPerson.setGender(user.gender());
    newPerson.setITIN(user.ITIN());
    newPerson.setUser(new User());
    newPerson.getUser().setAvatar(user.avatar());
    newPerson.getUser().setThemeImage(user.themeImage());
    newPerson.getUser().setUserIdentifier(user.userIdentifier());
    newPerson.getUser().setActive(user.active());

    newPerson.getUser().setActive(true);
    
    // personReturn.getUser().setPerson(newPerson);

    personReturn = userRepository.save(newPerson);

    return newPerson;
  }

  /**
   * Update a person by id
   * 
   * @param id
   * @param person
   * @return
   */
  public Person updateUser(Long id, UserRegistrationRequest user) {
    Person personToUpdate = null;

    personToUpdate = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

    if (personToUpdate == null) {
      throw new UserNotFoundException("User with id " + id + " not found");
    } else {
      personToUpdate.setFirstName(user.firstName());
      personToUpdate.setLastName(user.lastName());
      personToUpdate.setGender(user.gender());
      personToUpdate.setBirthDate(user.birthDate());
      personToUpdate.setITIN(user.ITIN());
      personToUpdate.setUpdatedDate(LocalDateTime.now());
      personToUpdate.getUser().setAvatar(user.avatar());
      personToUpdate.getUser().setThemeImage(user.themeImage());
      personToUpdate.getUser().setUserIdentifier(user.userIdentifier());
      personToUpdate.getUser().setUpdatedDate(LocalDateTime.now());
      
      personToUpdate = userRepository.save(personToUpdate);
    }

    return personToUpdate;
  }

  /**
   * Delete a person by id
   * 
   * @param id
   */
  public void deleteUser(Long id) {
    Person personToDelete = null;

    personToDelete = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

    if (personToDelete == null) {
      throw new UserNotFoundException("User with id " + id + " not found");
    } else {
      userRepository.delete(personToDelete);
    }
  }

}
