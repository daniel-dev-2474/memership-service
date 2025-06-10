package com.gymapp.membershipservice.exception;

import com.gymapp.common.exceptions.constants.ErrorMessages;
import com.gymapp.membershipservice.constant.Constant;
import com.gymapp.membershipservice.mapper.MembershipMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Constructor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

  private GlobalExceptionHandler handler;
  private HttpServletRequest mockRequest;

  @BeforeEach
  void setUp() {
    handler = new GlobalExceptionHandler();
    mockRequest = mock(HttpServletRequest.class);
    when(mockRequest.getRequestURI()).thenReturn("/test-uri");
  }

  @Test
  void shouldHandleValidationErrors() {
    FieldError fieldError = new FieldError("object", "field", "must not be null");
    BindingResult bindingResult = mock(BindingResult.class);
    when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

    MethodArgumentNotValidException ex =
        new MethodArgumentNotValidException(null, bindingResult);

    ResponseEntity<ErrorResponse> response = handler.handleValidationErrors(ex, mockRequest);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    ErrorResponse error = response.getBody();
    assertNotNull(error);
    assertEquals(HttpStatus.BAD_REQUEST.value(), error.getCode());
    assertEquals(ErrorMessages.BAD_REQUEST, error.getMessage());
    assertTrue(error.getDetails().contains("field: must not be null"));
    assertEquals("Review field constraints and input format.", error.getMoreInfo());
    assertNotNull(error.getTimestamp());
  }

  @Test
  void shouldHandleRuntimeException() {
    RuntimeException ex = new RuntimeException("Something went wrong");

    ResponseEntity<ErrorResponse> response = handler.handleRuntimeException(ex, mockRequest);

    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    ErrorResponse error = response.getBody();
    assertNotNull(error);
    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), error.getCode());
    assertEquals(ErrorMessages.BUSINESS_VALIDATION, error.getMessage());
    assertEquals("Something went wrong", error.getDetails());
    assertEquals("Check business rules or constraints for the requested operation.", error.getMoreInfo());
    assertNotNull(error.getTimestamp());
  }

  @Test
  void shouldException() {
    Exception ex = new Exception("Something went wrong");

    ResponseEntity<ErrorResponse> response = handler.handleUnhandledExceptions(ex, mockRequest);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    ErrorResponse error = response.getBody();
    assertNotNull(error);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), error.getCode());
    assertEquals(ErrorMessages.INTERNAL_SERVER_ERROR, error.getMessage());
    assertEquals("Exception: Exception", error.getDetails());
    assertEquals("Contact support with the error reference code.", error.getMoreInfo());
    assertNotNull(error.getTimestamp());
  }

}