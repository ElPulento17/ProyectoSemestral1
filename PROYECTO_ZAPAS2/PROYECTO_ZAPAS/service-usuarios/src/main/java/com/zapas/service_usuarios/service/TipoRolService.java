package com.zapas.service_usuarios.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zapas.service_usuarios.model.TipoRol;
import com.zapas.service_usuarios.repository.TipoRolRepository;

@Service
public class TipoRolService {

    @Autowired
    private TipoRolRepository tipoRolRepository;

    public List<TipoRol> listarTodos() {
        return tipoRolRepository.findAll();
    }

    public TipoRol guardar(TipoRol tipoRol) {
        return tipoRolRepository.save(tipoRol);
    }
}