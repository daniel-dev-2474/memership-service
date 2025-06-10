/**
 * This package contains membership-related classes.
 */
package com.gymapp.membershipservice.service.impl;

import com.gymapp.membershipservice.dto.MembershipResponse;
import com.gymapp.membershipservice.dto.MembershipRequest;
import com.gymapp.membershipservice.entity.Membership;
import com.gymapp.membershipservice.mapper.MembershipMapper;
import com.gymapp.membershipservice.repository.MembershipRepository;
import com.gymapp.membershipservice.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
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
   * Creates a new membership.
   *
   * @param dto the data for the new membership.
   * @return the created MembershipDTO.
   */
  @Override
  public MembershipResponse create(final MembershipRequest dto) {
    Membership membership = MembershipMapper.toEntity(dto);
    Membership save = repository.save(membership);
    return MembershipMapper.mapToDTO(save);
  }

  /**
   * Gets all memberships for a given user.
   *
   * @return list of MembershipDTO.
   */
  @Override
  public List<MembershipResponse> getMembershipsByUserId(final UUID userId) {
    List<Membership> memberships = repository.findByUserId(userId);
    return memberships.stream()
        .map(m -> MembershipResponse.builder()
            .id(m.getId())
            .startDate(m.getStartDate())
            .endDate(m.getEndDate())
            .name(m.getName())
            .build())
        .collect(Collectors.toList());
  }

}
