package com.example.report.domain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
   * @param Entity { <b>{@link Person}</b> }
   * @return {@code new User}
   */
  public Person registerUser(Person user) {
    Person newUser = null;
    Optional<Person> personExist = null;
    Person userReturn = null;

    personExist = userRepository.findPersonByFirstNameAndLastName(
        user.getFirstName(),
        user.getLastName()
      );

    if (!personExist.isEmpty()) {
      throw new UserAlreadyExistException("User already exists");
    } else {

      newUser = new Person(user);
      newUser.getUser().setActive(true);
      newUser.getUser().setPerson(newUser);

      userReturn = userRepository.save(newUser);
    }

    return userReturn;
  }

  /**
   * Update a person by {@code id}
   * 
   * @param id
   * @param Entity > { <b>{@link Person}</b> }
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
