package com.gymapp.membershipservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Request DTO for creating passes.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PassRequest {


  /**
   * User ID for pass.
   */
  @NotNull
  private UUID userId;

  /**
   * Number of passes that will be created.
   */
  @Min(1)
  private int quantity;
}
