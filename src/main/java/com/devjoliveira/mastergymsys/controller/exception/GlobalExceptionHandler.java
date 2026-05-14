package com.devjoliveira.mastergymsys.controller.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.devjoliveira.mastergymsys.domain.exception.BusinessException;
import com.devjoliveira.mastergymsys.domain.exception.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> validationErrorException(MethodArgumentNotValidException e) {

    List<String> messages = e.getBindingResult().getFieldErrors()
        .stream().map(error -> error.getField() + ":" + error.getDefaultMessage()).toList();

    ErrorResponse response = new ErrorResponse(
        LocalDateTime.now(),
        HttpStatus.BAD_GATEWAY.value(),
        "Validation Error",
        messages);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse> businessException(BusinessException e) {

    ErrorResponse response = new ErrorResponse(
        LocalDateTime.now(),
        HttpStatus.BAD_GATEWAY.value(),
        "Business Exception Error",
        List.of(e.getMessage()));

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

}