package com.zapas.service_resenas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zapas.service_resenas.model.Resena;
import com.zapas.service_resenas.service.ResenaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public List<Resena> listar() {
        return resenaService.listarTodas();
    }

    @GetMapping("/zapatilla/{zapatillaId}")
    public List<Resena> listarPorZapatilla(@PathVariable Long zapatillaId) {
        return resenaService.listarPorZapatilla(zapatillaId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resena> obtener(@PathVariable Long id) {
        Resena resena = resenaService.obtenerResenaCompleta(id);
        if (resena == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resena);
    }

    @PostMapping
    public ResponseEntity<Resena> crear(@RequestBody Resena resena) {
        return ResponseEntity.ok(resenaService.guardar(resena));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        resenaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}