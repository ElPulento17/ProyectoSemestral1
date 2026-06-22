package com.zapas.service_zapatillas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zapas.service_zapatillas.model.Marca;
import com.zapas.service_zapatillas.service.MarcaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public List<Marca> listar() {
        return marcaService.listarTodas();
    }

    @PostMapping
    public Marca crear(@RequestBody Marca marca) {
        return marcaService.guardar(marca);
    }
}