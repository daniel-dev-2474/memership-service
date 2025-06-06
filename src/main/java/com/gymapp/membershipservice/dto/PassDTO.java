package com.gymapp.membershipservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object for returning pass information.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PassDTO {

  /**
   * Unique identifier for the pass.
   */
  private Long id;

  /**
   * UUID of the user to whom the pass is assigned.
   */
  @NotBlank
  private UUID userId;

  /**
   * Date on which the pass was used, if applicable.
   */
  private LocalDateTime createAt;

}
