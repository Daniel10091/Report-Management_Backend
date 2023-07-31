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
        .orElseThrow(() -> new UserNotFoundException("Person not found"));
  }

  /**
   * Register a new person
   * 
   * @param person
   * @return
   */
  public Person registerUser(Person person) {
    Person newPerson = new Person();
    Person personExist = null;

    personExist = userRepository.findPersonByFirstNameAndLastName(
        person.getFirstName(),
        person.getLastName()
      ).get();

    if (personExist != null) {
      throw new UserAlreadyExistException("Person already exists");
    } else {

      person.getUser().setActive(true);
      
      newPerson.getUser().setPerson(person);

      newPerson = userRepository.save(person);
    }

    return newPerson;
  }

  /**
   * Update a person by id
   * 
   * @param id
   * @param person
   * @return
   */
  public Person updateUser(Long id, Person person) {
    Person personToUpdate = null;

    personToUpdate = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("Person not found"));

    if (personToUpdate == null) {
      throw new UserNotFoundException("Person not found");
    } else {
      personToUpdate.setFirstName(person.getFirstName());
      personToUpdate.setLastName(person.getLastName());
      personToUpdate.setGender(person.getGender());
      personToUpdate.setBirthDate(person.getBirthDate());
      personToUpdate.setITIN(person.getITIN());
      personToUpdate.getUser().setAvatar(person.getUser().getAvatar());
      personToUpdate.getUser().setThemeImage(person.getUser().getThemeImage());
      personToUpdate.getUser().setUserIdentifier(person.getUser().getUserIdentifier());
      personToUpdate.getUser().setActive(person.getUser().getActive());
      
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
        .orElseThrow(() -> new UserNotFoundException("Person not found"));

    if (personToDelete == null) {
      throw new UserNotFoundException("Person not found");
    } else {
      userRepository.delete(personToDelete);
    }
  }

}
