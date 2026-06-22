package com.zapas.service_notificaciones.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zapas.service_notificaciones.model.Notificacion;
import com.zapas.service_notificaciones.repository.NotificacionRepository;

@ExtendWith(MockitoExtension.class)
public class NotificacionServiceTest {

    @Mock
    private NotificacionRepository notificacionRepository;

    @InjectMocks
    private NotificacionService notificacionService;

    @Test
    public void guardarNotificacionTest() {

        Notificacion notificacion = new Notificacion();
        notificacion.setId(1L);
        notificacion.setUsuarioId(1L);
        notificacion.setMensaje("Tu pedido fue enviado");
        notificacion.setTipo("ENVIO");

        when(notificacionRepository.save(notificacion)).thenReturn(notificacion);

        Notificacion resultado = notificacionService.guardar(notificacion);

        assertNotNull(resultado);
        assertFalse(resultado.isLeida());
        assertEquals("ENVIO", resultado.getTipo());
    }

    @Test
    public void marcarComoLeidaTest() {

        Notificacion notificacion = new Notificacion();
        notificacion.setId(1L);
        notificacion.setLeida(false);

        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(notificacion));
        when(notificacionRepository.save(notificacion)).thenReturn(notificacion);

        Notificacion resultado = notificacionService.marcarComoLeida(1L);

        assertNotNull(resultado);
        assertEquals(true, resultado.isLeida());
    }
}