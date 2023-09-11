package com.example.report.domain.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.report.domain.dto.ErrorDto;
import com.example.report.domain.exception.RequestErrorException;
import com.example.report.domain.exception.UserAlreadyExistException;
import com.example.report.domain.exception.UserNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ UserNotFoundException.class })
  public ResponseEntity<ErrorDto> handleUserNotFound(RuntimeException ex, WebRequest request) {
    var errorDto = new ErrorDto(404, "Nenhum usuário encontrado", ex.getMessage(), request.getDescription(false));
    return ResponseEntity.status(errorDto.getStatus()).body(errorDto);
  }

  @ExceptionHandler(UserAlreadyExistException.class)
  public ResponseEntity<ErrorDto> handleUserAlreadyExist(RuntimeException ex, WebRequest request) {
    var errorDto = new ErrorDto(409, "Usuário já existe", ex.getMessage(), request.getDescription(false));
    return ResponseEntity.status(errorDto.getStatus()).body(errorDto);
  }

  @ExceptionHandler(RequestErrorException.class)
  public ResponseEntity<ErrorDto> handleRequestError(RuntimeException ex, WebRequest request) {
    var errorDto = new ErrorDto(400, "Erro de solicitação", ex.getMessage(), request.getDescription(false));
    return ResponseEntity.status(errorDto.getStatus()).body(errorDto);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorDto> handleOtherExceptions(RuntimeException ex, WebRequest request) {
    var errorDto = new ErrorDto(500, "Erro interno do servidor", ex.getMessage(), request.getDescription(false));
    return ResponseEntity.status(errorDto.getStatus()).body(errorDto);
  }

}
