package com.zapas.service_envios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zapas.service_envios.model.Envio;
import com.zapas.service_envios.repository.EnvioRepository;

@ExtendWith(MockitoExtension.class)
public class EnvioServiceTest {

    @Mock
    private EnvioRepository envioRepository;

    @InjectMocks
    private EnvioService envioService;

    @Test
    public void generarEnvioTest() {

        Envio envio = new Envio();
        envio.setId(1L);
        envio.setPedidoId(1L);
        envio.setEmpresaCourier("Chilexpress");
        envio.setNumeroSeguimiento("CX-98765");

        when(envioRepository.save(envio)).thenReturn(envio);

        Envio resultado = envioService.generarEnvio(envio);

        assertNotNull(resultado);
        assertEquals("PREPARANDO", resultado.getEstadoPaquete()); 
        assertEquals("Chilexpress", resultado.getEmpresaCourier());
    }

    @Test
    public void listarPorPedidoTest() {

        Envio envio = new Envio();
        envio.setId(1L);
        envio.setPedidoId(1L);

        when(envioRepository.findByPedidoId(1L)).thenReturn(java.util.List.of(envio));

        java.util.List<Envio> resultado = envioService.listarPorPedido(1L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }
}