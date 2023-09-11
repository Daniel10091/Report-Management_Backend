package com.example.report.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.report.domain.exception.RequestErrorException;
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
   * Get all users
   * 
   * @return {@code List<Users>}
   */
  public List<Person> getAllUsers(String option) {
    List<Person> users = null;

    if (!option.isEmpty() || !option.startsWith("all") || option != null) {
      switch(option) {
        case "online":
          users = userRepository.findAllOnlineUsers(true);
          break;
        case "offline":
          users = userRepository.findAllOnlineUsers(false);
          break;
        case "active":
          users = userRepository.findAllActiveUsers(true);
          break;
        case "inactive":
          users = userRepository.findAllActiveUsers(false);
          break;
        default:
          throw new RequestErrorException("Invalid option: " + option + " not found");
      }
    }

    users = userRepository.findAll();

    if (users.isEmpty()) 
      throw new UserNotFoundException("No users found");

    return users;
  }

  /**
   * Get all online users
   * 
   * @param status
   * @return {@code List<Person>}
   */
  public List<Person> getAllOnlineUsers(String option) {
    List<Person> users = null;

    switch(option) {
      case "online":

        users = userRepository.findAllOnlineUsers(true);
            
        if (users.isEmpty()) 
          throw new UserNotFoundException("No online users found");

        break;

      case "offline":

        users = userRepository.findAllOnlineUsers(false);
            
        if (users.isEmpty()) 
          throw new UserNotFoundException("No offline users found");

        break;

      default:
          
          throw new RequestErrorException("Invalid option: " + option + " not found");
    }

    return users;
  }

  /**
   * Get all active users
   * 
   * @param status
   * @return {@code List<Person>}
   */
  public List<Person> getAllActiveUsers(String option) {
    List<Person> users = null;

    switch (option) {
      case "active":
      
        users = userRepository.findAllActiveUsers(true);

        if (users.isEmpty()) 
          throw new UserNotFoundException("No active users found");

        break;

      case "inactive":

        users = userRepository.findAllActiveUsers(false);
            
        if (users.isEmpty()) 
          throw new UserNotFoundException("No inactive users found");

        break;
      
      default:
      
        throw new RequestErrorException("Invalid option: " + option + " not found");
    }

    return users;
  }

  /**
   * Find a person by {@code id}
   * 
   * @param id
   * @return <b>{@code Person}</b>
   */
  public Person findUserById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
  }

  /**
   * Get all inactive people
   * 
   * @return {@code List<Person>}
   */
  public List<Person> getAllInactiveUsers() {
    List<Person> users = null;

    

    

    return users;
  }

  /**
   * Register a new person
   * 
   * @param Entity { <b>{@link Person}</b> }
   * @return New <b>{@code Person}</b>
   */
  public Person registerUser(Person user) {
    Person newUser = null;
    Optional<Person> personExist = null;
    Person userReturn = null;

    personExist = userRepository.findPersonByUserUserIdentifier(
        user.getUser().getUserIdentifier()
      );

    if (!personExist.isEmpty()) {
      throw new UserAlreadyExistException("User already exists");
    } else {

      newUser = new Person(user);
      newUser.getUser().setOnline(true);
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
   * @return Updated <b>{@code Person}</b>
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
      personToUpdate.setUpdatedDate(LocalDateTime.now());
      personToUpdate.getUser().setAvatar(user.getUser().getAvatar());
      personToUpdate.getUser().setThemeImage(user.getUser().getThemeImage());
      personToUpdate.getUser().setUserIdentifier(user.getUser().getUserIdentifier());
      personToUpdate.getUser().setOnline(user.getUser().getOnline());
      personToUpdate.getUser().setActive(user.getUser().getActive());
      personToUpdate.getUser().setUpdatedDate(LocalDateTime.now());
      
      // personToUpdate = userRepository.save(personToUpdate);
    }

    return personToUpdate;
  }

  /**
   * Update the user status by {@code id}
   * 
   * @param id
   * @param online
   * @return Updated <b>{@code Person}</b> status
   */
  public Person updateUserStatus(Long id, Boolean online) {
    Person personToUpdate = null;

    personToUpdate = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

    if (personToUpdate == null) {
      throw new UserNotFoundException("User with id " + id + " not found");
    } else {
      personToUpdate.getUser().setOnline(online);
      personToUpdate.getUser().setUpdatedDate(LocalDateTime.now());
      
      // personToUpdate = userRepository.save(personToUpdate);
    }

    return personToUpdate;
  }

  /**
   * Update the user active by {@code id}
   * 
   * @param id
   * @param active
   * @return {@code Person}
   */
  public Person updateUserActive(Long id, Boolean active) {
    Person personToUpdate = null;

    personToUpdate = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

    if (personToUpdate == null) {
      throw new UserNotFoundException("User with id " + id + " not found");
    } else {
      personToUpdate.getUser().setActive(active);
      personToUpdate.getUser().setUpdatedDate(LocalDateTime.now());
      
      // personToUpdate = userRepository.save(personToUpdate);
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
