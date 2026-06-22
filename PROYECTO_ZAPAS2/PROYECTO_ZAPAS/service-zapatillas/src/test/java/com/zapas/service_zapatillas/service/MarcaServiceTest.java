package com.zapas.service_zapatillas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zapas.service_zapatillas.model.Marca;
import com.zapas.service_zapatillas.repository.MarcaRepository;

@ExtendWith(MockitoExtension.class)
public class MarcaServiceTest {

    @Mock
    private MarcaRepository marcaRepository;

    @InjectMocks
    private MarcaService marcaService;

    @Test
    public void guardarMarcaTest() {

        Marca marca = new Marca();
        marca.setId(1L);
        marca.setNombre("Nike");

        when(marcaRepository.save(marca)).thenReturn(marca);

        Marca resultado = marcaService.guardar(marca);

        assertNotNull(resultado);
        assertEquals("Nike", resultado.getNombre());
    }

    @Test
    public void listarTodasTest() {

        Marca marca = new Marca();
        marca.setId(1L);
        marca.setNombre("Adidas");

        when(marcaRepository.findAll()).thenReturn(List.of(marca));

        List<Marca> resultado = marcaService.listarTodas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Adidas", resultado.get(0).getNombre());
    }
}