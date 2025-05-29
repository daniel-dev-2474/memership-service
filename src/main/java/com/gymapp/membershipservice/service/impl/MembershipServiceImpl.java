package com.gymapp.membershipservice.service.impl;

import com.gymapp.membershipservice.constant.Constant;
import com.gymapp.membershipservice.dto.MembershipDTO;
import com.gymapp.membershipservice.entity.Membership;
import com.gymapp.membershipservice.repository.MembershipRepository;
import com.gymapp.membershipservice.service.MembershipService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MembershipServiceImpl implements MembershipService {

  private final MembershipRepository repository;

  public MembershipServiceImpl(MembershipRepository repository) {
    this.repository = repository;
  }

  public MembershipDTO mapToDTO(Membership entity) {
    return MembershipDTO.builder()
        .id(entity.getId())
        .name(entity.getName())
        .includedPasses(entity.getIncludedPasses())
        .build();
  }

  public Membership mapToEntity(MembershipDTO dto) {
    return Membership.builder()
        .name(dto.getName())
        .includedPasses(dto.getIncludedPasses())
        .build();
  }

  @Override
  public MembershipDTO create(MembershipDTO dto) {
    Membership membership = mapToEntity(dto);
    return mapToDTO(repository.save(membership));
  }

  @Override
  public List<MembershipDTO> findAll() {
    return repository.findAll().stream()
        .filter(Membership::getActive)
        .map(this::mapToDTO)
        .collect(Collectors.toList());
  }

  @Override
  public MembershipDTO findById(Long id) {
    return repository.findById(id)
        .filter(Membership::getActive)
        .map(this::mapToDTO)
        .orElseThrow(() -> new RuntimeException(Constant.MEMBERSHIP_NOT_FOUND));
  }

  @Override
  public MembershipDTO update(Long id, MembershipDTO dto) {
    Membership membership = repository.findById(id)
        .orElseThrow(() -> new RuntimeException(Constant.MEMBERSHIP_NOT_FOUND));
    membership.setName(dto.getName());
    membership.setIncludedPasses(dto.getIncludedPasses());
    return mapToDTO(repository.save(membership));
  }

  @Override
  public void update(Long id) {
    Membership membership = repository.findById(id)
        .filter(Membership::getActive)
        .orElseThrow(() -> new RuntimeException(Constant.MEMBERSHIP_NOT_FOUND));
    membership.setActive(false);
    repository.save(membership);

  }
}
