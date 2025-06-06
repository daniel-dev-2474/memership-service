package com.gymapp.membershipservice.service;

import com.gymapp.membershipservice.dto.MembershipDTO;
import com.gymapp.membershipservice.dto.MembershipRequest;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing memberships.
 *
 * @author daniel-dev-2474
 */
public interface MembershipService {

  /**
   * Creates a new membership.
   *
   * @param dto the data for the new membership.
   * @return the created MembershipDTO.
   */
  MembershipDTO create(MembershipRequest dto);

  /**
   * Retrieves a list of all memberships.
   *
   * @param userId for the user passes.
   * @return list of MembershipDTO.
   */
  List<MembershipDTO> getMembershipsByUserId(UUID userId);
}
