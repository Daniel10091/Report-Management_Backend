package com.example.report.domain.exception;

public class PersonAlreadyExistException extends RuntimeException {
  
  /**
   * @param message
   */
  public PersonAlreadyExistException(String message) {
    super(message);
  }

  /**
   * @param message
   * @param cause
   */
  public PersonAlreadyExistException(String message, Throwable cause) {
    super(message, cause);
  }

}
