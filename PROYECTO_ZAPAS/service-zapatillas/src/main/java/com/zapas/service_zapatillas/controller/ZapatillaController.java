package com.zapas.service_zapatillas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zapas.service_zapatillas.model.Zapatilla;
import com.zapas.service_zapatillas.service.ZapatillaService;

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
        if(zapatillas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(zapatillas);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Zapatilla>> listarPorEstado(@PathVariable String estado) {
        List<Zapatilla> zapatillas = zapatillaService.buscarPorEstado(estado);
        if(zapatillas.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(zapatillas);
    }

    @PostMapping
    public ResponseEntity<Zapatilla> crear(@RequestBody Zapatilla zapatilla) {
        return ResponseEntity.ok(zapatillaService.guardar(zapatilla));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        zapatillaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}