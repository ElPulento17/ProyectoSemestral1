package com.zapas.service_usuarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zapas.service_usuarios.model.TipoRol;
import com.zapas.service_usuarios.repository.TipoRolRepository;

@ExtendWith(MockitoExtension.class)
public class TipoRolServiceTest {

    @Mock
    private TipoRolRepository tipoRolRepository;

    @InjectMocks
    private TipoRolService tipoRolService;

    @Test
    public void listarTodosTest() {
        
        TipoRol rol = new TipoRol();
        rol.setId(1L);
        rol.setNombre("CLIENTE");

        when(tipoRolRepository.findAll()).thenReturn(List.of(rol));

        List<TipoRol> resultado = tipoRolService.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("CLIENTE", resultado.get(0).getNombre());
    }

    @Test
    public void guardarTest() {
        
        TipoRol rol = new TipoRol();
        rol.setId(1L);
        rol.setNombre("VENDEDOR");

        when(tipoRolRepository.save(rol)).thenReturn(rol);

        TipoRol resultado = tipoRolService.guardar(rol);

        assertNotNull(resultado);
        assertEquals("VENDEDOR", resultado.getNombre());
    }
}