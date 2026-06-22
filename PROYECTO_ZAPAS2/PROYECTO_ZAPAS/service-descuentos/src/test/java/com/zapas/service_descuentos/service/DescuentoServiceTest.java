package com.zapas.service_descuentos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zapas.service_descuentos.model.Descuento;
import com.zapas.service_descuentos.repository.DescuentoRepository;

@ExtendWith(MockitoExtension.class)
public class DescuentoServiceTest {

    @Mock
    private DescuentoRepository descuentoRepository; 

    @InjectMocks
    private DescuentoService descuentoService; 

    @Test
    public void guardarDescuentoTest() {

        Descuento descuento = new Descuento();
        descuento.setId(1L);
        descuento.setCodigo("VERANO20");
        descuento.setPorcentaje(20);
        descuento.setActivo(true);

        when(descuentoRepository.save(descuento)).thenReturn(descuento);

        Descuento resultado = descuentoService.guardar(descuento);

        assertNotNull(resultado);
        assertEquals("VERANO20", resultado.getCodigo());
        assertEquals(20, resultado.getPorcentaje());
    }

    @Test
    public void buscarPorIdTest() {

        Descuento descuento = new Descuento();
        descuento.setId(1L);
        descuento.setCodigo("PROMO10");

        when(descuentoRepository.findById(1L)).thenReturn(Optional.of(descuento));

        Optional<Descuento> resultado = descuentoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("PROMO10", resultado.get().getCodigo());
    }
}