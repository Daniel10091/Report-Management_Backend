package com.example.report.domain.exception;

public class UserAlreadyExistException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;
  
  /**
   * @param message
   */
  public UserAlreadyExistException(String message) {
    super(message);
  }

  /**
   * @param message
   * @param cause
   */
  public UserAlreadyExistException(String message, Throwable cause) {
    super(message, cause);
  }

}
