package com.gymapp.membershipservice.controller;

import com.gymapp.membershipservice.constant.Constant;
import com.gymapp.membershipservice.dto.MembershipDTO;
import com.gymapp.membershipservice.service.MembershipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
   * @return the created MembershipDTO.
   */

  @PostMapping
  public MembershipDTO create(@RequestBody @Valid final MembershipDTO dto) {
    return service.create(dto);
  }

  /**
   * Retrieves a list of all memberships.
   *
   * @return list of MembershipDTO.
   */
  @GetMapping
  public List<MembershipDTO> findAll() {
    return service.findAll();
  }

  /**
   * Retrieves a specific membership by its ID.
   *
   * @param id the ID of the membership to retrieve.
   * @return the MembershipDTO if found.
   */
  @GetMapping(Constant.PARAM_ID)
  public MembershipDTO findById(@PathVariable final Long id) {
    return service.findById(id);
  }

  /**
   * Updates an existing membership.
   *
   * @param id the ID of the membership to update.
   * @param dto the updated membership data.
   * @return the updated MembershipDTO.
   */
  @PutMapping(Constant.PARAM_ID)
  public MembershipDTO update(
      @PathVariable final Long id, final @RequestBody @Valid MembershipDTO dto
  ) {
    return service.update(id, dto);
  }

  /**
   * Deletes a membership by ID.
   *
   * @param id the ID of the membership to delete.
   */
  @DeleteMapping(Constant.PARAM_ID)
  public void delete(@PathVariable final Long id) {
    service.update(id);
  }
}
