package com.gymapp.membershipservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class BaseEntity {

  @Column(nullable = false)
  protected Boolean active = true;

  @Column(updatable = false)
  protected LocalDateTime createdAt;

  protected LocalDateTime modifiedAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
    this.modifiedAt = this.createdAt;
  }

  @PreUpdate
  protected void onUpdate() {
      this.modifiedAt = LocalDateTime.now();
  }
}
