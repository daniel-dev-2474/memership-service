package com.gymapp.membershipservice.mapper;

import com.gymapp.membershipservice.dto.MembershipResponse;
import com.gymapp.membershipservice.dto.MembershipRequest;
import com.gymapp.membershipservice.entity.Membership;

/**
 * Utility class for mapping DTOs to Membership entities.
 */
public final class MembershipMapper {

  /**
   * Private constructor.
   */
  private MembershipMapper() {
    throw new UnsupportedOperationException("Utility class");
  }

  /**
   * Maps a MembershipDTO request to Membership entity.
   *
   * @param request the membership request to be converted.
   * @return the corresponding Membership entity.
   */
  public static Membership toEntity(final MembershipRequest request) {
    return Membership.builder()
        .userId(request.getUserId())
        .startDate(request.getStartDate())
        .endDate(request.getEndDate())
        .name(request.getName())
        .build();
  }

  /**
   * Maps a Membership entity to MembershipDTO.
   *
   * @param entity the membership entity to be converted.
   * @return the corresponding MembershipDTO.
   */
  public static MembershipResponse mapToDTO(final Membership entity) {
    return MembershipResponse.builder()
        .id(entity.getId())
        .name(entity.getName())
        .startDate(entity.getStartDate())
        .endDate(entity.getEndDate())
        .build();
  }

}
