package com.gymapp.membershipservice.controller;

import com.gymapp.membershipservice.constant.Constant;
import com.gymapp.membershipservice.dto.MembershipResponse;
import com.gymapp.membershipservice.dto.MembershipRequest;
import com.gymapp.membershipservice.service.MembershipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing Membership resources.
 *
 * @author daniel-dev-2474
 */
@CrossOrigin
@RestController
@RequestMapping(Constant.API_MEMBERSHIP)
@RequiredArgsConstructor
public class MembershipController {

  /**
   * Service for managing Membership entities.
   */
  private final MembershipService service;


  /**
   * Creates a new membership.
   *
   * @param dto the data for the new membership.
   * @return the created MembershipRequest.
   */

  @PostMapping
  public MembershipResponse create(@RequestBody @Valid final MembershipRequest dto) {
    return service.create(dto);
  }

  /**
   * Retrieves a specific membership by its ID.
   *
   * @param userId the ID of the membership to retrieve.
   * @return the MembershipDTO if found.
   */
  @GetMapping(Constant.PARAM_ID)
  public List<MembershipResponse> getMembershipsByUserId(
      @PathVariable final UUID userId
  ) {
    return service.getMembershipsByUserId(userId);
  }

}
