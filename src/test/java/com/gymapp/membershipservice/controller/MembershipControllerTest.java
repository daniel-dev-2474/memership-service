package com.gymapp.membershipservice.controller;

import com.gymapp.membershipservice.dto.MembershipDTO;
import com.gymapp.membershipservice.entity.Membership;
import com.gymapp.membershipservice.service.MembershipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MembershipControllerTest {

  @Mock
  private MembershipService service;

  @InjectMocks
  private MembershipController controller;

  private Membership membership;

  private MembershipDTO dto;

  @BeforeEach
  void setUp() {
    membership = Membership.builder()
        .id(1L)
        .name("Premium")
        .includedPasses(10)
        .build();

    dto = MembershipDTO.builder()
        .id(1L)
        .name("Premium")
        .includedPasses(10)
        .build();
  }

  @Test
  void testCreateMembresia() {
    when(service.create(any())).thenReturn(dto);
    MembershipDTO resultado = controller.create(dto);

    assertEquals("Premium", resultado.getName());
    assertEquals(10, resultado.getIncludedPasses());
    verify(service).create(any());
  }

  @Test
  void test_findById() {
    when(service.findById(any())).thenReturn(dto);

    MembershipDTO resultado = service.findById(1L);

    assertEquals(1, resultado.getId());
    assertEquals("Premium", resultado.getName());
  }

  @Test
  void test_findByIdFail() {
    when(service.findById(2L)).thenThrow(new RuntimeException("Exception"));

    assertThrows(RuntimeException.class, () -> {
      service.findById(2L);
    });
  }

  @Test
  void listar_retornarListaDtos() {
    when(service.findAll()).thenReturn(List.of(dto));

    List<MembershipDTO> resultado = controller.findAll();
    assertEquals(1, resultado.size());
    assertEquals("Premium", resultado.get(0).getName());
  }

  @Test
  void actualizar_deberiaActualizarYRetornarDTO() {
    MembershipDTO actualizacion = MembershipDTO.builder()
        .name("Actualizada")
        .includedPasses(12)
        .build();

    when(service.update(any(), any())).thenReturn(dto);
    MembershipDTO resultado = service.update(1L, actualizacion);
  }

  @Test
  void actualizar_deberiaLanzarExcepcionSiNoExiste() {
    when(service.update(any(), any())).thenThrow(new RuntimeException("Exception"));
    assertThrows(RuntimeException.class, () -> {
      service.update(2L, dto);
    });
  }


}