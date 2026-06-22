package com.zapas.service_usuarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zapas.service_usuarios.model.Usuario;
import com.zapas.service_usuarios.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void guardarUsuarioTest() {
        
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setRun("12345678-9");
        usuario.setNombre("Juan Pérez");
        usuario.setCorreo("juan@mail.com");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.guardar(usuario);

        assertNotNull(resultado);
        assertEquals("Juan Pérez", resultado.getNombre());
    }

    @Test
    public void buscarPorIdTest() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setRun("12345678-9");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("12345678-9", resultado.get().getRun());
    }
}