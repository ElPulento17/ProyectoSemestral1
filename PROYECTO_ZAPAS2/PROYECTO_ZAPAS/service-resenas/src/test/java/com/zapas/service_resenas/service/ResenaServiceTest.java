package com.zapas.service_resenas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zapas.service_resenas.model.Resena;
import com.zapas.service_resenas.repository.ResenaRepository;

@ExtendWith(MockitoExtension.class)
public class ResenaServiceTest {

    @Mock
    private ResenaRepository resenaRepository;

    @InjectMocks
    private ResenaService resenaService;

    @Test
    public void guardarResenaTest() {

        Resena resena = new Resena();
        resena.setId(1L);
        resena.setUsuarioId(1L);
        resena.setZapatillaId(1L);
        resena.setPuntuacion(5);
        resena.setComentario("Excelente calidad");

        when(resenaRepository.save(resena)).thenReturn(resena);

        Resena resultado = resenaService.guardar(resena);

        assertNotNull(resultado);
        assertEquals(5, resultado.getPuntuacion());
        assertEquals("Excelente calidad", resultado.getComentario());
    }

    @Test
    public void listarPorZapatillaTest() {

        Resena resena = new Resena();
        resena.setId(1L);
        resena.setZapatillaId(1L);
        resena.setPuntuacion(4);

        when(resenaRepository.findByZapatillaId(1L)).thenReturn(List.of(resena));

        List<Resena> resultado = resenaService.listarPorZapatilla(1L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(4, resultado.get(0).getPuntuacion());
    }
}