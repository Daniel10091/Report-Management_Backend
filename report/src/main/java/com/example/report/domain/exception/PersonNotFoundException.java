package com.example.report.domain.exception;

public class PersonNotFoundException extends RuntimeException {
  
  /**
   * @param message
   */
  public PersonNotFoundException(String message) {
    super(message);
  }

  /**
   * @param message
   * @param cause
   */
  public PersonNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

}
