package com.zapas.service_usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zapas.service_usuarios.model.TipoRol;
import com.zapas.service_usuarios.service.TipoRolService; 

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/usuario/tipos")
public class TipoRolController {

    private TipoRolService tipoRolService;

    @GetMapping
    public List<TipoRol> listar() {
        return tipoRolService.listarTodos();
    }

    @PostMapping
    public TipoRol crear(@RequestBody TipoRol tipoRol) {
        return tipoRolService.guardar(tipoRol);
    }
}