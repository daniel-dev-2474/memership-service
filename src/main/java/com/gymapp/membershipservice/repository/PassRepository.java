package com.gymapp.membershipservice.repository;

import com.gymapp.membershipservice.entity.Pass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for Pass entities.
 */
public interface PassRepository extends JpaRepository<Pass, Long> {


  /**
   * Finds all passes by user ID.
   *
   * @param userId UUID of the user.
   * @return List of passes.
   */
  List<Pass> findByUserId(UUID userId);
}
