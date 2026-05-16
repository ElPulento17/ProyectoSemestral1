package com.zapas.service_envios.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zapas.service_envios.model.Envio;
import com.zapas.service_envios.service.EnvioService;

@RestController
@RequestMapping("/api/v1/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public List<Envio> listar() {
        return envioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> verTracking(@PathVariable Long id) {
        Envio envio = envioService.obtenerTrackingCompleto(id);
        if(envio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(envio);
    }

    @PostMapping
    public ResponseEntity<Envio> despachar(@RequestBody Envio envio) {
        return ResponseEntity.ok(envioService.generarEnvio(envio));
    }
}