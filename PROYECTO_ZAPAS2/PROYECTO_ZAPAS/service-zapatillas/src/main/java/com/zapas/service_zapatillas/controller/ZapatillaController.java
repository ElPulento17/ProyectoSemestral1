package com.zapas.service_zapatillas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zapas.service_zapatillas.model.StockZapatilla;
import com.zapas.service_zapatillas.model.Zapatilla;
import com.zapas.service_zapatillas.service.ZapatillaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/zapatillas")
public class ZapatillaController {

    @Autowired
    private ZapatillaService zapatillaService;  

    @GetMapping
    public List<Zapatilla> listar() {
        return zapatillaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zapatilla> obtener(@PathVariable Long id) {
        return zapatillaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/marca/{marcaId}")
    public ResponseEntity<List<Zapatilla>> listarPorMarca(@PathVariable Long marcaId) {
        List<Zapatilla> zapatillas = zapatillaService.buscarPorMarca(marcaId);
        if (zapatillas.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(zapatillas);
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Zapatilla>> listarPorGenero(@PathVariable String genero) {
        List<Zapatilla> zapatillas = zapatillaService.listarTodas().stream()
        .filter(z -> z.getGenero().equalsIgnoreCase(genero)).toList();
        if (zapatillas.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(zapatillas);
    }

    @PostMapping
    public ResponseEntity<Zapatilla> crear(@RequestBody Zapatilla zapatilla) {
        return ResponseEntity.ok(zapatillaService.guardar(zapatilla));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Zapatilla> actualizar(@PathVariable Long id, @RequestBody Zapatilla zapatilla) {
        return zapatillaService.actualizar(id, zapatilla)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        zapatillaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/stock")
    public ResponseEntity<List<StockZapatilla>> verStock(@PathVariable Long id) {
        List<StockZapatilla> stock = zapatillaService.listarStockPorZapatilla(id);
        if (stock.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(stock);
    }

    @GetMapping("/{id}/stock/disponible")
    public ResponseEntity<List<StockZapatilla>> verStockDisponible(@PathVariable Long id) {
        List<StockZapatilla> stock = zapatillaService.listarStockDisponible(id);
        if (stock.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(stock);
    }

    @PostMapping("/{id}/stock")
    public ResponseEntity<StockZapatilla> agregarStock(@PathVariable Long id, @RequestBody StockZapatilla stock) {
        return ResponseEntity.ok(zapatillaService.guardarStock(id, stock));
    }

    @PutMapping("/stock/{stockId}")
    public ResponseEntity<StockZapatilla> actualizarStock(@PathVariable Long stockId, @RequestBody StockZapatilla stock) {
        return zapatillaService.actualizarStock(stockId, stock)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}