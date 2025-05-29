package com.gymapp.membershipservice.service.impl;

import com.gymapp.membershipservice.dto.MembershipDTO;
import com.gymapp.membershipservice.entity.Membership;
import com.gymapp.membershipservice.repository.MembershipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MembershipServiceImplTest {

  @Mock
  private MembershipRepository repository;

  @InjectMocks
  private MembershipServiceImpl service;

  private Membership membership;

  private MembershipDTO dto;

  @BeforeEach
  void setUp() {
    membership = Membership.builder()
        .id(1L)
        .nombre("Premium")
        .pasesIncluidos(10)
        .build();

    dto = MembershipDTO.builder()
        .name("Premium")
        .includedPasses(10)
        .build();
  }

  @Test
  void testCrearMembresia() {
    when(repository.save(any(Membership.class))).thenReturn(membership);

    MembershipDTO resultado = service.create(dto);

    assertEquals("Premium", resultado.getName());
    assertEquals(10, resultado.getIncludedPasses());
    verify(repository).save(any());
  }

  @Test
  void test_findById() {
    when(repository.findById(any())).thenReturn(Optional.of(membership));

    MembershipDTO resultado = service.findById(1L);

    assertEquals(1, resultado.getId());
    assertEquals("Premium", resultado.getName());
  }

  @Test
  void test_findByIdFail() {
    when(repository.findById(2L)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> {
      service.findById(2L);
    });
  }

  @Test
  void listarActivos_deberiaRetornarListaDTOs() {
    when(repository.findAll()).thenReturn(List.of(membership));

    List<MembershipDTO> resultado = service.findAll();

    assertEquals(1, resultado.size());
    assertEquals("Premium", resultado.get(0).getName());
  }

  @Test
  void actualizar_deberiaActualizarYRetornarDTO() {
    MembershipDTO actualizacion = MembershipDTO.builder()
        .name("Actualizada")
        .includedPasses(12)
        .build();

    when(repository.findById(1L)).thenReturn(Optional.of(membership));
    when(repository.save(any())).thenReturn(membership);

    MembershipDTO resultado = service.update(1L, actualizacion);

    assertEquals("Actualizada", resultado.getName());
    assertEquals(12, resultado.getIncludedPasses());
  }

  @Test
  void actualizar_deberiaLanzarExcepcionSiNoExiste() {
    when(repository.findById(2L)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> {
      service.update(2L, dto);
    });
  }

  @Test
  void eliminar_deberiaMarcarComoInactivo() {
    when(repository.findById(1L)).thenReturn(Optional.of(membership));

    service.update(1L);

    assertFalse(membership.getActive());
    verify(repository).save(membership);
  }

  @Test
  void eliminar_deberiaLanzarExcepcionSiNoExiste() {
    when(repository.findById(99L)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> {
      service.update(99L);
    });
  }

}