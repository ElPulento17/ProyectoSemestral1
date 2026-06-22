package com.zapas.service_zapatillas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zapas.service_zapatillas.model.StockZapatilla;

@Repository
public interface StockZapatillaRepository extends JpaRepository<StockZapatilla, Long> {

    // Buscar todo el stock de una zapatilla
    List<StockZapatilla> findByZapatillaId(Long zapatillaId);

    // Buscar stock por talla específica
    List<StockZapatilla> findByTalla(int talla);

    // Buscar stock disponible (cantidad > 0) de una zapatilla
    List<StockZapatilla> findByZapatillaIdAndCantidadGreaterThan(Long zapatillaId, int cantidad);
}