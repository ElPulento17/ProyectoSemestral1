package com.zapas.service_zapatillas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zapas.service_zapatillas.model.StockZapatilla;
import com.zapas.service_zapatillas.model.Zapatilla;
import com.zapas.service_zapatillas.repository.StockZapatillaRepository;
import com.zapas.service_zapatillas.repository.ZapatillaRepository;

import jakarta.transaction.Transactional;

@Service
public class ZapatillaService {

    @Autowired
    private ZapatillaRepository zapatillaRepository;

    @Autowired
    private StockZapatillaRepository stockRepository;

    public List<Zapatilla> listarTodas() {
        return zapatillaRepository.findAll();
    }

    public Optional<Zapatilla> buscarPorId(Long id) {
        return zapatillaRepository.findById(id);
    }

    public List<Zapatilla> buscarPorMarca(Long marcaId) {
        return zapatillaRepository.findByMarcaId(marcaId);
    }

    @Transactional
    public Zapatilla guardar(Zapatilla zapatilla) {
        return zapatillaRepository.save(zapatilla);
    }

    @Transactional
    public Optional<Zapatilla> actualizar(Long id, Zapatilla zapatilla) {
        return zapatillaRepository.findById(id)
                .map(existente -> {
                    zapatilla.setId(id);
                    return zapatillaRepository.save(zapatilla);
                });
    }

    public void eliminar(Long id) {
        zapatillaRepository.deleteById(id);
    }

    public List<StockZapatilla> listarStockPorZapatilla(Long zapatillaId) {
        return stockRepository.findByZapatillaId(zapatillaId);
    }

    // Solo tallas con stock disponible
    public List<StockZapatilla> listarStockDisponible(Long zapatillaId) {
        return stockRepository.findByZapatillaIdAndCantidadGreaterThan(zapatillaId, 0);
    }

    @Transactional
    public StockZapatilla guardarStock(Long zapatillaId, StockZapatilla stock) {
        Zapatilla zapatilla = zapatillaRepository.findById(zapatillaId)
                .orElseThrow(() -> new RuntimeException("Zapatilla no encontrada: " + zapatillaId));
        stock.setZapatilla(zapatilla);
        return stockRepository.save(stock);
    }

    @Transactional
    public Optional<StockZapatilla> actualizarStock(Long stockId, StockZapatilla nuevoStock) {
        return stockRepository.findById(stockId)
                .map(existente -> {
                    existente.setCantidad(nuevoStock.getCantidad());
                    existente.setPrecio(nuevoStock.getPrecio());
                    existente.setEstado(nuevoStock.getEstado());
                    return stockRepository.save(existente);
                });
    }
}