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


/**
 * Centralized exception handler that intercepts and handles application
 * exceptions, providing meaningful error responses to the client.
 */
@RestControllerAdvice
public final class GlobalExceptionHandler {

  /**
   * Exception handler for managing bad request caused by
   * validation errors.
   *
   * @param ex the method argument not valid exception instance.
   * @param request the HTTP request information.
   * @return an error DTO containing details about the cause of the error.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationErrors(
      final MethodArgumentNotValidException ex, final HttpServletRequest request
  ) {
    String details = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> error.getField()
            + Constant.COLON_SEPARATOR + error.getDefaultMessage())
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

  /**
   * Exception handler for managing runtime exceptions caused by
   * validation errors.
   *
   * @param ex the runtime exception instance.
   * @param request the HTTP request information.
   * @return an error DTO containing details about the cause of the error.
   */
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(
      final RuntimeException ex, final HttpServletRequest request
  ) {
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
