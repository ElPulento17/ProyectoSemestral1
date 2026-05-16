package com.zapas.service_zapatillas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zapas.service_zapatillas.model.Marca;
import com.zapas.service_zapatillas.repository.MarcaRepository;
import jakarta.transaction.Transactional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> listarTodas() {
        return marcaRepository.findAll();
    }

    @Transactional
    public Marca guardar(Marca marca) {
        return marcaRepository.save(marca);
    }
}