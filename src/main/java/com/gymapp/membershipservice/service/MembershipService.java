package com.gymapp.membershipservice.service;

import com.gymapp.membershipservice.dto.MembershipDTO;

import java.util.List;

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
  MembershipDTO create(MembershipDTO dto);

  /**
   * Retrieves a list of all memberships.
   *
   * @return list of MembershipDTO.
   */
  List<MembershipDTO> findAll();

  /**
   * Retrieves a specific membership by its ID.
   *
   * @param id the ID of the membership to retrieve.
   * @return the MembershipDTO if found.
   */
  MembershipDTO findById(Long id);

  /**
   * Updates an existing membership.
   *
   * @param id the ID of the membership to update.
   * @param dto the updated membership data.
   * @return the updated MembershipDTO.
   */
  MembershipDTO update(Long id, MembershipDTO dto);

  /**
   * Deletes a membership by ID.
   *
   * @param id the ID of the membership to delete.
   */
  void update(Long id);
}
