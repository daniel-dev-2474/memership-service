package com.gymapp.membershipservice.controller;
import com.gymapp.membershipservice.dto.MembershipResponse;
import com.gymapp.membershipservice.dto.MembershipRequest;
import com.gymapp.membershipservice.service.MembershipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link MembershipController}.
 */
@ExtendWith(MockitoExtension.class)
class MembershipControllerTest {

  @Mock
  private MembershipService membershipService;

  @InjectMocks
  private MembershipController membershipController;

  private MembershipRequest request;
  private MembershipResponse dto;

  private UUID userId;

  /**
   * Setup before each test.
   */
  @BeforeEach
  void setUp() {
    userId = UUID.randomUUID();

    request = MembershipRequest.builder()
        .userId(userId)
        .startDate(LocalDate.now())
        .endDate(LocalDate.now().plusMonths(1))
        .build();

    dto = MembershipResponse.builder()
        .id(1L)
        .startDate(request.getStartDate())
        .endDate(request.getEndDate())
        .build();
  }

  /**
   * Tests that a new membership is successfully created.
   */
  @Test
  void testCreateMembership() {
    when(membershipService.create(request)).thenReturn(dto);

    MembershipResponse result = membershipController.create(request);

    assertThat(result).isNotNull();
    assertThat(result.getStartDate()).isEqualTo(request.getStartDate());
    verify(membershipService, times(1)).create(request);
  }

  /**
   * Tests retrieving memberships by userId.
   */
  @Test
  void testGetMembershipsByUserId() {
    when(membershipService.getMembershipsByUserId(userId)).thenReturn(List.of(dto));

    List<MembershipResponse> result = membershipController.getMembershipsByUserId(userId);

    assertThat(result).hasSize(1);
    verify(membershipService, times(1)).getMembershipsByUserId(userId);
  }
}