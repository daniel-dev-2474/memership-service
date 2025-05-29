package com.gymapp.membershipservice.service;

import com.gymapp.membershipservice.dto.MembershipDTO;

import java.util.List;

public interface MembershipService {
  MembershipDTO create(MembershipDTO dto);
  List<MembershipDTO> findAll();
  MembershipDTO findById(Long id);
  MembershipDTO update(Long id, MembershipDTO dto);

  void update(Long id);
}
