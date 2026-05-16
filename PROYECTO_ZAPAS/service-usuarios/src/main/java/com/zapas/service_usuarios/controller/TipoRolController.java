package com.zapas.service_usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zapas.service_usuarios.model.TipoRol;
import com.zapas.service_usuarios.repository.TipoRolRepository;

@RestController
@RequestMapping("/api/v1/usuario/tipos")
public class TipoRolController {

    @Autowired
    private TipoRolRepository repository;

    @GetMapping
    public List<TipoRol> listar(){
        return repository.findAll();
    }

    @PostMapping
    public TipoRol crear(@RequestBody TipoRol tipoRol){
        return repository.save(tipoRol);
    }

}
