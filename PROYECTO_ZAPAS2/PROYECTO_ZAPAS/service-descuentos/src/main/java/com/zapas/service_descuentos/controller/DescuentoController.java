package com.zapas.service_descuentos.controller;

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

import com.zapas.service_descuentos.model.Descuento;
import com.zapas.service_descuentos.service.DescuentoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/descuentos")
public class DescuentoController {

    @Autowired
    private DescuentoService descuentoService;

    @GetMapping
    public List<Descuento> listar() {
        return descuentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Descuento> obtener(@PathVariable Long id) {
        return descuentoService.buscarPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Descuento> crear(@RequestBody Descuento descuento) {
        return ResponseEntity.ok(descuentoService.guardar(descuento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Descuento> actualizar(@PathVariable Long id, @RequestBody Descuento descuento) {
        return descuentoService.buscarPorId(id).map(existente -> {
            descuento.setId(id);
            return ResponseEntity.ok(descuentoService.guardar(descuento));})
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        descuentoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}