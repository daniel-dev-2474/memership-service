package com.gymapp.membershipservice.dto;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Request DTO for creating a membership.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembershipRequest {

  /**
   * ID of the user to whom this membership belongs.
   */
  @NotNull
  private UUID userId;

  /**
   * Start date of the membership validity.
   */
  @NotNull
  private LocalDate startDate;

  /**
   * End date of the membership validity.
   */
  @NotNull
  @Future
  private LocalDate endDate;

  /**
   * Name of the membership plan (e.g., Monthly, Quarterly).
   */
  @NotBlank
  private String name;
}
