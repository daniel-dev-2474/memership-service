package com.gymapp.membershipservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents an active gym membership assigned to a user.
 */

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Membership extends BaseEntity {

  /**
   * The unique identifier for the membership plan.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * ID of the user to whom this membership belongs.
   */
  @Column(nullable = false, updatable = false)
  private UUID userId;

  /**
   * Start date of the membership validity.
   */
  @Column(nullable = false)
  private LocalDate startDate;

  /**
   * End date of the membership validity.
   */
  @Column(nullable = false)
  private LocalDate endDate;

  /**
   * Name of the membership plan (e.g., Monthly, Quarterly).
   */
  private String name;

}
