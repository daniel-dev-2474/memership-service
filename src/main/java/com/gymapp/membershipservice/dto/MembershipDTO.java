package com.gymapp.membershipservice.dto;

import com.gymapp.membershipservice.constant.Constant;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembershipDTO {

  private Long id;

  @NotBlank(message = Constant.NAME_REQUIRED)
  private String name;


  @Min(value = 1, message = Constant.PASSES_REQUIRED)
  private Integer includedPasses;
}
