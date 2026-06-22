package com.zapas.service_proveedores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zapas.service_proveedores.model.ProveedorMarca;
import com.zapas.service_proveedores.service.ProveedorMarcaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedorMarcaController {

    @Autowired
    private ProveedorMarcaService proveedorMarcaService;

    // este asociar una marca al proveedor
    @PostMapping("/{id}/marcas")
    public ProveedorMarca asociarMarca(@PathVariable Long id, @RequestBody ProveedorMarca proveedorMarca) {
        return proveedorMarcaService.asociarMarca(id, proveedorMarca);
    }

    // este vee la marcas que suministr4a al proveedor
    @GetMapping("/{id}/marcas")
    public List<ProveedorMarca> listarMarcas(@PathVariable Long id) {
        return proveedorMarcaService.listarPorProveedor(id);
    }
}