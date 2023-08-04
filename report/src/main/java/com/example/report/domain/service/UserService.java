package com.example.report.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.report.domain.exception.UserAlreadyExistException;
import com.example.report.domain.exception.UserNotFoundException;
import com.example.report.domain.model.Person;
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
   * @return {@code All Users}
   */
  public List<Person> getAllUsers() {
    return userRepository.findAll();
  }

  /**
   * Find a person by {@code id}
   * 
   * @param id
   * @return {@code User}
   */
  public Person findUserById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
  }

  /**
   * Register a new person
   * 
   * @param person
   * @return {@code new User}
   */
  public Person registerUser(Person user) {
    Person newUser = new Person();
    Person personExist = null;
    Person userReturn = null;

    personExist = userRepository.findPersonByFirstNameAndLastName(
        user.getFirstName(),
        user.getLastName()
      ).get();

    if (personExist != null) {
      throw new UserAlreadyExistException("Person already exists");
    } else {

      user.getUser().setActive(true);
      
      newUser.getUser().setPerson(newUser);

      newUser = userRepository.save(newUser);
    }

    return userReturn;
  }

  /**
   * Update a person by {@code id}
   * 
   * @param id
   * @param person
   * @return {@code updated User}
   */
  public Person updateUser(Long id, Person user) {
    Person personToUpdate = null;

    personToUpdate = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

    if (personToUpdate == null) {
      throw new UserNotFoundException("User with id " + id + " not found");
    } else {
      personToUpdate.setFirstName(user.getFirstName());
      personToUpdate.setLastName(user.getLastName());
      personToUpdate.setGender(user.getGender());
      personToUpdate.setBirthDate(user.getBirthDate());
      personToUpdate.setItin(user.getItin());
      personToUpdate.getUser().setAvatar(user.getUser().getAvatar());
      personToUpdate.getUser().setThemeImage(user.getUser().getThemeImage());
      personToUpdate.getUser().setUserIdentifier(user.getUser().getUserIdentifier());
      personToUpdate.getUser().setActive(user.getUser().getActive());
      
      personToUpdate = userRepository.save(personToUpdate);
    }

    return personToUpdate;
  }

  /**
   * Delete a person by {@code id}
   * 
   * @param id
   * @throws Exception
   */
  public void deleteUser(Long id) throws Exception {
    Person personToDelete = null;

    personToDelete = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

    try {
      userRepository.delete(personToDelete);
    } catch (Exception e) {
      System.out.println("[ ERROR ] -> Error to delete user: " + e.getMessage());
    }
  }

}
