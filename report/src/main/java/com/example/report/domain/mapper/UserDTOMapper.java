package com.example.report.domain.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.example.report.domain.dto.UserDTO;
import com.example.report.domain.model.Person;

@Service
public class UserDTOMapper implements Function<Person, UserDTO> {

  @Override
  public UserDTO apply(Person entity) {
    return new UserDTO(
      entity.getId(),
      entity.getFirstName(),
      entity.getLastName(),
      entity.getUser().getUserIdentifier(),
      entity.getGender(),
      entity.getBirthDate(),
      entity.getITIN(),
      entity.getUser().getActive(),
      entity.getUser().getAvatar(),
      entity.getUser().getThemeImage()
    );
  }

}
