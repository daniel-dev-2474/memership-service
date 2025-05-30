package com.gymapp.membershipservice.dto;

import com.gymapp.membershipservice.constant.Constant;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Data transfer object represents a membership plan in the gym system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembershipDTO {

  /**
   * The unique identifier for the membership plan.
   */
  private Long id;

  /**
   * The name of the membership plan.
   * Example: "Basic", "Premium".
   */
  @NotBlank(message = Constant.NAME_REQUIRED)
  private String name;

  /**
   * The number of passes included with the membership.
   */
  @Min(value = 1, message = Constant.PASSES_REQUIRED)
  private Integer includedPasses;
}
