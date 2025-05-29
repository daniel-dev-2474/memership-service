package com.gymapp.membershipservice.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorResponse {

  private int code;

  private String message;

  private String details;

  private String moreInfo;

  private LocalDateTime timestamp;

}
