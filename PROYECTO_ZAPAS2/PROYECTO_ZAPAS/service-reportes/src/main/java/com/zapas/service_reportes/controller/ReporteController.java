package com.zapas.service_reportes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zapas.service_reportes.model.Reporte;
import com.zapas.service_reportes.service.ReporteService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public List<Reporte> listar() {
        return reporteService.listarTodos();
    }

    // genera el reporte nuevo consultandole a service pedidos
    @PostMapping("/generar")
    public Reporte generar() {
        return reporteService.generarReporteVentas();
    }
}