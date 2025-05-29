package com.gymapp.membershipservice.exception;

import com.gymapp.membershipservice.constant.Constant;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
    String details = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> error.getField() + Constant.COLON_SEPARATOR + error.getDefaultMessage())
        .collect(Collectors.joining(Constant.COMMA_SEPARATOR));
    ErrorResponse error = ErrorResponse.builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .message(Constant.FAILED_VALIDATION)
        .details(details)
        .moreInfo(request.getRequestURI())
        .timestamp(LocalDateTime.now())
        .build();
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
    ErrorResponse error = ErrorResponse.builder()
        .code(HttpStatus.UNPROCESSABLE_ENTITY.value())
        .message(Constant.BUSINESS_VALIDATION)
        .details(ex.getMessage())
        .moreInfo(request.getRequestURI())
        .timestamp(LocalDateTime.now())
        .build();
    return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
  }
}
