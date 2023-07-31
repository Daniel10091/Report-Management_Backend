package com.example.report.domain.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.report.domain.exception.PersonAlreadyExistException;
import com.example.report.domain.exception.PersonNotFoundException;
import com.example.report.domain.model.Person;
import com.example.report.domain.repository.PersonRepository;
import com.example.report.domain.util.UserFilesUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PersonService {

  @Value("${project.files.user-files}")
  private String basePath;

  private PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  /**
   * Get all people
   * 
   * @return
   */
  public List<Person> getPeople() {
    return personRepository.findAll();
  }

  /**
   * Find a person by id
   * 
   * @param id
   * @return
   */
  public Person findPersonById(Long id) {
    return personRepository.findById(id)
        .orElseThrow(() -> new PersonNotFoundException("Person not found"));
  }

  /**
   * Register a new person
   * 
   * @param person
   * @return
   */
  public Person registerPerson(Person person) {
    Person newPerson = new Person();
    Person personExist = null;

    personExist = personRepository.findPersonByFirstNameAndLastName(
        person.getFirstName(),
        person.getLastName()
      ).get();

    if (personExist != null) {
      throw new PersonAlreadyExistException("Person already exists");
    } else {
      // TODO: Create user avatar

      
      // TODO: Create user theme image


      // newPerson.getUser().setPerson(person);
      newPerson = personRepository.save(person);
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
  public Person updatePerson(Long id, Person person) {
    Person personToUpdate = null;

    personToUpdate = personRepository.findById(id)
        .orElseThrow(() -> new PersonNotFoundException("Person not found"));

    if (personToUpdate == null) {
      throw new PersonNotFoundException("Person not found");
    } else {
      personToUpdate.setFirstName(person.getFirstName());
      personToUpdate.setLastName(person.getLastName());
      personToUpdate.setBirthDate(person.getBirthDate());
      personToUpdate.setGender(person.getGender());
      personToUpdate.setITIN(person.getITIN());
      // personToUpdate.getUser().setUserIdentifier(person.getUser().getUserIdentifier());
      // personToUpdate.getUser().setAvatarPath(person.getUser().getAvatarPath());
      // personToUpdate.getUser().setThemeImagePath(person.getUser().getThemeImagePath());

      // TODO: Delete actual user avatar


      // TODO: Delete actual user theme image

      
      personToUpdate = personRepository.save(personToUpdate);
    }

    return personToUpdate;
  }

  /**
   * Delete a person by id
   * 
   * @param id
   */
  public void deletePerson(Long id) {
    Person personToDelete = null;

    personToDelete = personRepository.findById(id)
        .orElseThrow(() -> new PersonNotFoundException("Person not found"));

    if (personToDelete == null) {
      throw new PersonNotFoundException("Person not found");
    } else {
      personRepository.delete(personToDelete);
    }
  }

  public Object saveUserAvatar(byte[] avatarBytes) {
    InputStream in = new ByteArrayInputStream(avatarBytes);
    return UserFilesUtil.createUserAvatar(basePath, "daniel10091", in);
  }

}
