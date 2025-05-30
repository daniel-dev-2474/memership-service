package com.gymapp.membershipservice.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


/**
 * Data transfer Object representing the structure of an error response.
 */
@Getter
@Setter
@Builder
public class ErrorResponse {

  /**
   * HTTP status code associated with the error (e.g. 400,404,500).
   */
  private int code;

  /**
   * Short, human-readable description of the error.
   */
  private String message;

  /**
   * Additional technical or contextual details about the error.
   */
  private String details;

  /**
   * Optional URL or reference to more information about the error.
   */
  private String moreInfo;

  /**
   * The date and time when the error occurred.
   */
  private LocalDateTime timestamp;

}
