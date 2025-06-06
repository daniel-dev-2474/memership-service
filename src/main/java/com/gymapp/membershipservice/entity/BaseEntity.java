package com.gymapp.membershipservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Base class for entities providing audit fields like createdAt,
 * modifiedAt and active flag.
 */
@MappedSuperclass
@Data
public class BaseEntity {

  /**
   * Indicated whether the entity is active.
   */
  @Column(nullable = false)
  private Boolean active = true;

  /**
   * Timestamp of when the entity was created.
   */
  @Column(updatable = false)
  private LocalDateTime createdAt;

  /**
   * timestamp of when the entity was last updated.
   */
  private LocalDateTime modifiedAt;

  /**
   * Called before the entity is persisted.
   * Subclasses may override this to perform custom logic before creation.
   */
  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
    this.modifiedAt = this.createdAt;
  }

  /**
   * Called before the entity is updated.
   * Subclasses may override this to perform custom logic before update.
   */
  @PreUpdate
  protected void onUpdate() {
      this.modifiedAt = LocalDateTime.now();
  }
}
