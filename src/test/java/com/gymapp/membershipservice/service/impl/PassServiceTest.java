package com.gymapp.membershipservice.service.impl;

import com.gymapp.membershipservice.dto.PassDTO;
import com.gymapp.membershipservice.dto.PassRequest;
import com.gymapp.membershipservice.entity.Pass;
import com.gymapp.membershipservice.repository.PassRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link PassService}
 */
@ExtendWith(MockitoExtension.class)
class PassServiceTest {

  @Mock
  private PassRepository repository;

  @InjectMocks
  private PassService service;

  private PassRequest request;

  private Pass pass;

  private UUID userId;

  /**
   * Set up data before each test.
   */
  @BeforeEach
  void setUp() {
    userId = UUID.randomUUID();

    request = PassRequest.builder()
        .userId(userId)
        .quantity(5)
        .build();
  }

  @Test
  void shouldCreateAndSaveCorrectPasses() {
    PassRequest request = new PassRequest();
    request.setUserId(userId);
    request.setQuantity(3);

    service.createPasses(request);

    ArgumentCaptor<List<Pass>> captor = ArgumentCaptor.forClass(List.class);
    verify(repository).saveAll(captor.capture());

    List<Pass> savedPasses = captor.getValue();
    assertEquals(3, savedPasses.size());
    for (Pass pass : savedPasses) {
      assertEquals(userId, pass.getUserId());
      assertFalse(pass.isUsed());
    }
  }
  @Test
  void shouldReturnUsedPassesAsDTOs() {
    UUID userId = UUID.randomUUID();

    List<Pass> mockPasses = List.of(
        Pass.builder().id(1L).userId(userId).used(true).build(),
        Pass.builder().id(2L).userId(userId).used(false).build(),
        Pass.builder().id(3L).userId(userId).used(true).build()
    );
    when(repository.findByUserId(userId)).thenReturn(mockPasses);

    List<PassDTO> result = service.getPassesByUserId(userId);

    assertEquals(2, result.size());
    assertTrue(result.stream().allMatch(dto -> dto.getUserId().equals(userId)));
  }
}