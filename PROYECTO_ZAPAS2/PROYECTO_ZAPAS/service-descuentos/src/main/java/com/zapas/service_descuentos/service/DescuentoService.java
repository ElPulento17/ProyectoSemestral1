package com.zapas.service_descuentos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zapas.service_descuentos.model.Descuento;
import com.zapas.service_descuentos.repository.DescuentoRepository;

import jakarta.transaction.Transactional;

@Service
public class DescuentoService {

    @Autowired
    private DescuentoRepository descuentoRepository;

    public List<Descuento> listarTodos() {
        return descuentoRepository.findAll();
    }

    public Optional<Descuento> buscarPorId(Long id) {
        return descuentoRepository.findById(id);
    }

    @Transactional
    public Descuento guardar(Descuento descuento) {
        return descuentoRepository.save(descuento);
    }

    public void eliminar(Long id) {
        descuentoRepository.deleteById(id);
    }
}