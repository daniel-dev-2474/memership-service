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
 * Represents a single-use pass that allows access to the gym.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Pass extends BaseEntity {

  /**
   * Unique identifier for the pass.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * UUID of the user to whom the pass is assigned.
   */
  @Column(nullable = false, columnDefinition = "UUID")
  private UUID userId;

  /**
   * Indicates whether the pass has been used.
   */
  @Column(nullable = false)
  private boolean used;

  /**
   * Date on which the pass was used, if applicable.
   */
  private LocalDate usedDate;

}
