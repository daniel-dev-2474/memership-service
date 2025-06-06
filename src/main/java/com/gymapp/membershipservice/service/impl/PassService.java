package com.gymapp.membershipservice.service.impl;

import com.gymapp.membershipservice.dto.PassDTO;
import com.gymapp.membershipservice.dto.PassRequest;
import com.gymapp.membershipservice.entity.Pass;
import com.gymapp.membershipservice.repository.PassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * Service for managing the passes of users.
 */
@Service
@RequiredArgsConstructor
public class PassService {

  /**
   * Repository used to manage Pass entities via JPA.
   */
  private final PassRepository passRepository;

  /**
   * Creates one or more passes for a user.
   *
   * @param request CreatePassRequest containing userId and quantity.
   */
  public void createPasses(final PassRequest request) {

    List<Pass> passes = IntStream.range(0, request.getQuantity())
        .mapToObj(i -> Pass.builder()
            .userId(request.getUserId())
            .used(false)
            .build())
        .toList();
    passRepository.saveAll(passes);
  }

  /**
   * Gets all passes for a given user.
   *
   * @param userId UUID of the user.
   * @return List of pass DTOs.
   */
  public List<PassDTO> getPassesByUserIdd(final UUID userId) {
    List<Pass> passes = passRepository.findByUserId(userId);
    return passes.stream().filter(Pass::isUsed)
        .map(p -> PassDTO.builder()
            .id(p.getId())
            .userId(p.getUserId())
            .createAt(p.getCreatedAt())
            .build())
        .toList();
  }
}
