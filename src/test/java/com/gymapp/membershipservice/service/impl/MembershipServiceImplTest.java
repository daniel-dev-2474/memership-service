package com.gymapp.membershipservice.service.impl;

import com.gymapp.membershipservice.dto.MembershipResponse;
import com.gymapp.membershipservice.dto.MembershipRequest;
import com.gymapp.membershipservice.entity.Membership;
import com.gymapp.membershipservice.mapper.MembershipMapper;
import com.gymapp.membershipservice.repository.MembershipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link MembershipServiceImpl}.
 */
@ExtendWith(MockitoExtension.class)
class MembershipServiceImplTest {

  @Mock
  private MembershipRepository repository;

  @InjectMocks
  private MembershipServiceImpl service;

  private MembershipRequest request;
  private Membership membership;
  private UUID userId;

  /**
   * Set up data before each test.
   */
  @BeforeEach
  void setUp() {
    userId = UUID.randomUUID();

    request = MembershipRequest.builder()
        .userId(userId)
        .startDate(LocalDate.now())
        .endDate(LocalDate.now().plusMonths(1))
        .name("Mensual")
        .build();

    membership = MembershipMapper.toEntity(request);
    membership.setId(1L);
  }

  /**
   * Test the creation of a new membership.
   */
  @Test
  void testCreateMembership() {
    when(repository.save(any(Membership.class))).thenReturn(membership);

    MembershipResponse result = service.create(request);

    assertThat(result).isNotNull();
    assertThat(result.getName()).isEqualTo(request.getName());

    // Optional: verify values sent to save()
    ArgumentCaptor<Membership> captor = ArgumentCaptor.forClass(Membership.class);
    verify(repository).save(captor.capture());
    assertThat(captor.getValue().getUserId()).isEqualTo(request.getUserId());
  }

  /**
   * Test retrieving all memberships by user ID.
   */
  @Test
  void testGetMembershipsByUserId() {
    when(repository.findByUserId(userId)).thenReturn(List.of(membership));

    List<MembershipResponse> result = service.getMembershipsByUserId(userId);

    assertThat(result).hasSize(1);
    assertThat(result.get(0).getName()).isEqualTo(membership.getName());

    verify(repository).findByUserId(userId);
  }
}