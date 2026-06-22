package com.zapas.service_pagos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zapas.service_pagos.model.Pago;
import com.zapas.service_pagos.repository.PagoRepository;

@ExtendWith(MockitoExtension.class)
public class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoService pagoService;

    @Test
    public void procesarPagoTest() {

        Pago pago = new Pago();
        pago.setId(1L);
        pago.setPedidoId(1L);
        pago.setMonto(85000);
        pago.setMetodo("webpay");

        when(pagoRepository.save(pago)).thenReturn(pago);

        Pago resultado = pagoService.procesarPago(pago);

        assertNotNull(resultado);
        assertEquals("APROBADO", resultado.getEstado()); 
        assertNotNull(resultado.getFechaPago()); 
    }

    @Test
    public void listarPorPedidoTest() {

        Pago pago = new Pago();
        pago.setId(1L);
        pago.setPedidoId(1L);

        when(pagoRepository.findByPedidoId(1L)).thenReturn(java.util.List.of(pago));

        java.util.List<Pago> resultado = pagoService.listarPorPedido(1L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }
}