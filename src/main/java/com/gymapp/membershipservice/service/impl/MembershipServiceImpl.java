package com.gymapp.membershipservice.service.impl;

import com.gymapp.membershipservice.constant.Constant;
import com.gymapp.membershipservice.dto.MembershipDTO;
import com.gymapp.membershipservice.entity.Membership;
import com.gymapp.membershipservice.repository.MembershipRepository;
import com.gymapp.membershipservice.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for managing memberships.
 *
 * @author daniel-dev-2474
 */
@Service
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {

  /**
   * Repository used to manage Membership entities via JPA.
   */
  private final MembershipRepository repository;

  /**
   * Maps a Membership entity to MembershipDTO.
   *
   * @param entity the membership entity to be converted.
   * @return the corresponding MembershipDTO.
   */
  public MembershipDTO mapToDTO(final Membership entity) {
    return MembershipDTO.builder()
        .id(entity.getId())
        .name(entity.getName())
        .includedPasses(entity.getIncludedPasses())
        .build();
  }

  /**
   * Maps a MembershipDTO request to Membership entity.
   *
   * @param dto the membership request to be converted.
   * @return the corresponding Membership entity.
   */
  public Membership mapToEntity(final MembershipDTO dto) {
    return Membership.builder()
        .name(dto.getName())
        .includedPasses(dto.getIncludedPasses())
        .build();
  }

  /**
   * Creates a new membership.
   *
   * @param dto the data for the new membership.
   * @return the created MembershipDTO.
   */
  @Override
  public MembershipDTO create(final MembershipDTO dto) {
    Membership membership = mapToEntity(dto);
    return mapToDTO(repository.save(membership));
  }

  /**
   * Retrieves a list of all memberships.
   *
   * @return list of MembershipDTO.
   */
  @Override
  public List<MembershipDTO> findAll() {
    return repository.findAll().stream()
        .filter(Membership::getActive)
        .map(this::mapToDTO)
        .collect(Collectors.toList());
  }

  /**
   * Retrieves a specific membership by its ID.
   *
   * @param id the ID of the membership to retrieve.
   * @return the MembershipDTO if found.
   */
  @Override
  public MembershipDTO findById(final Long id) {
    return repository.findById(id)
        .filter(Membership::getActive)
        .map(this::mapToDTO)
        .orElseThrow(() -> new RuntimeException(Constant.MEMBERSHIP_NOT_FOUND));
  }

  /**
   * Updates an existing membership.
   *
   * @param id the ID of the membership to update.
   * @param dto the updated membership data.
   * @return the updated MembershipDTO.
   */
  @Override
  public MembershipDTO update(final Long id, final MembershipDTO dto) {
    Membership membership = repository.findById(id)
        .orElseThrow(() -> new RuntimeException(Constant.MEMBERSHIP_NOT_FOUND));
    membership.setName(dto.getName());
    membership.setIncludedPasses(dto.getIncludedPasses());
    return mapToDTO(repository.save(membership));
  }

  /**
   * Deletes a membership by ID.
   *
   * @param id the ID of the membership to delete.
   */
  @Override
  public void update(final Long id) {
    Membership membership = repository.findById(id)
        .filter(Membership::getActive)
        .orElseThrow(() -> new RuntimeException(Constant.MEMBERSHIP_NOT_FOUND));
    membership.setActive(false);
    repository.save(membership);

  }
}
