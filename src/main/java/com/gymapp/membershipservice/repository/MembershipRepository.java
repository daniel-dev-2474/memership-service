package com.gymapp.membershipservice.repository;

import com.gymapp.membershipservice.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for Membership entities.
 */
@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {

  /**
   * Finds all memberships by user ID.
   *
   * @param userId UUID of the user.
   * @return List of memberships.
   */
  List<Membership> findByUserId(UUID userId);

}
