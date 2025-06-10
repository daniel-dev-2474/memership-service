package com.gymapp.membershipservice.exception;

import com.gymapp.common.exceptions.constants.ErrorMessages;
import com.gymapp.membershipservice.constant.Constant;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
      final MethodArgumentNotValidException ex,
      final HttpServletRequest request
  ) {
    String details = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> error.getField()
            + Constant.COLON_SEPARATOR + error.getDefaultMessage())
        .collect(Collectors.joining(Constant.COMMA_SEPARATOR));
    log.warn("{} Validation error on path={} - details={}",
        Constant.GLOBAL_HANDLER, request.getRequestURI(), details);
    ErrorResponse error = ErrorResponse.builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .message(ErrorMessages.BAD_REQUEST)
        .details(details)
        .moreInfo("Review field constraints and input format.")
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
    log.error("{} Business logic exception on path={} - error={}",
        Constant.GLOBAL_HANDLER, request.getRequestURI(), ex.getMessage(), ex);
    ErrorResponse error = ErrorResponse.builder()
        .code(HttpStatus.UNPROCESSABLE_ENTITY.value())
        .message(ErrorMessages.BUSINESS_VALIDATION)
        .details(ex.getMessage())
        .moreInfo(
            "Check business rules or constraints for the requested operation."
        ).timestamp(LocalDateTime.now())
        .build();
    return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  /**
   * Exception handler for managing internal server error caused by
   * unknown errors.
   *
   * @param ex the method argument instance.
   * @param request the HTTP request information.
   * @return an error DTO containing details about the cause of the error.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleUnhandledExceptions(
      final Exception ex, final HttpServletRequest request
  ) {
    log.error("{} Unhandled exception on path={} - errorClass={}, message={}",
        Constant.GLOBAL_HANDLER, request.getRequestURI(),
        ex.getClass().getSimpleName(), ex.getMessage());

    ErrorResponse error = ErrorResponse.builder()
        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message(ErrorMessages.INTERNAL_SERVER_ERROR)
        .timestamp(LocalDateTime.now())
        .details("Exception: " + ex.getClass().getSimpleName())
        .moreInfo("Contact support with the error reference code.")
        .build();

    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
