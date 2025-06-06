package com.gymapp.membershipservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gymapp.membershipservice.dto.PassDTO;
import com.gymapp.membershipservice.dto.PassRequest;
import com.gymapp.membershipservice.service.impl.PassService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PassController.class)
class PassControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private PassService passService;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void shouldCallServiceToCreatePassesAndReturnCreated() throws Exception {
    PassRequest request = PassRequest.builder()
        .userId(UUID.randomUUID())
        .quantity(3)
        .build();

    mockMvc.perform(
        post("/api/pass")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated());

    verify(passService).createPasses(any(PassRequest.class));
  }

  @Test
  void shouldReturnPassesByUserId() throws Exception {
    UUID userId = UUID.randomUUID();
    List<PassDTO> passes = List.of(
        PassDTO.builder().id(1L).userId(userId).createAt(LocalDateTime.now()).build(),
        PassDTO.builder().id(2L).userId(userId).createAt(LocalDateTime.now()).build()
    );

    when(passService.getPassesByUserId(userId)).thenReturn(passes);

    mockMvc.perform(get("/api/pass/{userId}", userId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(passes.size()))
        .andExpect(jsonPath("$[0].userId").value(userId.toString()));

    verify(passService).getPassesByUserId(userId);
  }
}