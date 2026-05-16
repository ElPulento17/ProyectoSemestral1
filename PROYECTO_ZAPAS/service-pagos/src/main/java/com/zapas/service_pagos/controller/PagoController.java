package com.zapas.service_pagos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zapas.service_pagos.model.Pago;
import com.zapas.service_pagos.service.PagoService;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public List<Pago> listar() {
        return pagoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerComprobante(@PathVariable Long id) {
        Pago pago = pagoService.obtenerComprobanteCompleto(id);
        if(pago == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pago);
    }

    @PostMapping
    public ResponseEntity<Pago> pagar(@RequestBody Pago pago) {
        return ResponseEntity.ok(pagoService.procesarPago(pago));
    }
}