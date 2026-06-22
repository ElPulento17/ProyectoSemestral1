package com.zapas.service_reportes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zapas.service_reportes.model.Reporte;
import com.zapas.service_reportes.repository.ReporteRepository;

@ExtendWith(MockitoExtension.class)
public class ReporteServiceTest {

    @Mock
    private ReporteRepository reporteRepository;

    @InjectMocks
    private ReporteService reporteService;

    @Test
    public void listarTodosTest() {
        
        Reporte reporte1 = new Reporte();
        reporte1.setId(1L);
        reporte1.setTipo("VENTAS_GENERAL");
        reporte1.setTotalVentas(150000);
        reporte1.setTotalPedidos(3);

        when(reporteRepository.findAll()).thenReturn(List.of(reporte1));

        List<Reporte> resultado = reporteService.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("VENTAS_GENERAL", resultado.get(0).getTipo());
    }
}