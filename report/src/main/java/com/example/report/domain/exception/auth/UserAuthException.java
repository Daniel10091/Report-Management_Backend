package com.example.report.domain.exception.auth;

public class UserAuthException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;

  /**
   * @param message
   */
  public UserAuthException(String message) {
    super(message);
  }

  /**
   * @param message
   * @param cause
   */
  public UserAuthException(String message, Throwable cause) {
    super(message, cause);
  }

}
