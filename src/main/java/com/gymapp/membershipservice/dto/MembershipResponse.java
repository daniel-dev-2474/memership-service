package com.gymapp.membershipservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


/**
 * Data transfer object for returning membership information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembershipResponse {

  /**
   * The unique identifier for the membership plan.
   */
  private Long id;

  /**
   * Start date of the membership validity.
   */
  private LocalDate startDate;

  /**
   * End date of the membership validity.
   */
  private LocalDate endDate;

  /**
   * Name of the membership plan (e.g., Monthly, Quarterly).
   */
  private String name;

}
