package com.gymapp.membershipservice.controller;

import com.gymapp.membershipservice.constant.Constant;
import com.gymapp.membershipservice.dto.MembershipDTO;
import com.gymapp.membershipservice.service.MembershipService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(Constant.API_MEMBERSHIP)
public class MembershipController {

  private final MembershipService service;

  public MembershipController(MembershipService service) {
    this.service = service;
  }

  @PostMapping
  public MembershipDTO create(@RequestBody @Valid MembershipDTO dto) {
    return service.create(dto);
  }

  @GetMapping
  public List<MembershipDTO> findAll() {
    return service.findAll();
  }


  @GetMapping(Constant.PARAM_ID)
  public MembershipDTO findById(@PathVariable Long id) {
    return service.findById(id);
  }

  @PutMapping(Constant.PARAM_ID)
  public MembershipDTO update(@PathVariable Long id, @RequestBody @Valid MembershipDTO dto) {
    return service.update(id, dto);
  }

  @DeleteMapping(Constant.PARAM_ID)
  public void delete(@PathVariable Long id) {
    service.update(id);
  }
}
