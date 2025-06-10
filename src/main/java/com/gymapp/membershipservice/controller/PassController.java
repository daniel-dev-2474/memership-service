package com.gymapp.membershipservice.controller;

import com.gymapp.membershipservice.constant.Constant;
import com.gymapp.membershipservice.dto.PassResponse;
import com.gymapp.membershipservice.dto.PassRequest;
import com.gymapp.membershipservice.service.impl.PassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Constant.API_PASS)
@RequiredArgsConstructor
public class PassController {

  /**
   * Service for managing Pass entities.
   */
  private final PassService passService;

  /**
   * Creates one or more passes for a user.
   *
   * @param request CreatePassRequest containing userId and quantity.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createPasses(@Valid @RequestBody final PassRequest request) {
    passService.createPasses(request);
  }

  /**
   * Gets all passes for a given user.
   *
   * @param userId UUID of the user.
   * @return List of pass DTOs.
   */
  @GetMapping(Constant.PARAM_ID)
  public List<PassResponse> getPassesByUserId(@PathVariable final UUID userId) {
    return passService.getPassesByUserId(userId);
  }

}
