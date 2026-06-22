package com.zapas.service_zapatillas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zapas.service_zapatillas.model.StockZapatilla;
import com.zapas.service_zapatillas.model.Zapatilla;
import com.zapas.service_zapatillas.repository.StockZapatillaRepository;
import com.zapas.service_zapatillas.repository.ZapatillaRepository;

@ExtendWith(MockitoExtension.class)
public class ZapatillaServiceTest {

    @Mock
    private ZapatillaRepository zapatillaRepository;

    @Mock
    private StockZapatillaRepository stockRepository;

    @InjectMocks
    private ZapatillaService zapatillaService;

    @Test
    public void guardarZapatillaTest() {

        Zapatilla zapatilla = new Zapatilla();
        zapatilla.setId(1L);
        zapatilla.setModelo("Air Force 1");
        zapatilla.setColor("Blanco");
        zapatilla.setGenero("unisex");

        when(zapatillaRepository.save(zapatilla)).thenReturn(zapatilla);

        Zapatilla resultado = zapatillaService.guardar(zapatilla);

        assertNotNull(resultado);
        assertEquals("Air Force 1", resultado.getModelo());
    }

    @Test
    public void buscarPorIdTest() {

        Zapatilla zapatilla = new Zapatilla();
        zapatilla.setId(1L);
        zapatilla.setModelo("Stan Smith");

        when(zapatillaRepository.findById(1L)).thenReturn(Optional.of(zapatilla));

        Optional<Zapatilla> resultado = zapatillaService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Stan Smith", resultado.get().getModelo());
    }

    @Test
    public void guardarStockTest() {

        Zapatilla zapatilla = new Zapatilla();
        zapatilla.setId(1L);
        zapatilla.setModelo("Air Force 1");

        StockZapatilla stock = new StockZapatilla();
        stock.setId(1L);
        stock.setTalla(42);
        stock.setCantidad(10);
        stock.setPrecio(85000);

        when(zapatillaRepository.findById(1L)).thenReturn(Optional.of(zapatilla));
        when(stockRepository.save(stock)).thenReturn(stock);

        StockZapatilla resultado = zapatillaService.guardarStock(1L, stock);

        assertNotNull(resultado);
        assertEquals(42, resultado.getTalla());
        assertEquals(zapatilla, resultado.getZapatilla());
    }

    @Test
    public void listarStockDisponibleTest() {

        StockZapatilla stock = new StockZapatilla();
        stock.setId(1L);
        stock.setCantidad(5);

        when(stockRepository.findByZapatillaIdAndCantidadGreaterThan(1L, 0))
                .thenReturn(List.of(stock));

        List<StockZapatilla> resultado = zapatillaService.listarStockDisponible(1L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }
}