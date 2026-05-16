package com.zapas.service_zapatillas.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zapas.service_zapatillas.model.Zapatilla;
import com.zapas.service_zapatillas.repository.ZapatillaRepository;
import jakarta.transaction.Transactional;

@Service
public class ZapatillaService {

    @Autowired
    private ZapatillaRepository zapatillaRepository;

    public List<Zapatilla> listarTodas() {
        return zapatillaRepository.findAll();
    }

    public Optional<Zapatilla> buscarPorId(Long id) {
        return zapatillaRepository.findById(id);
    }
    
    public List<Zapatilla> buscarPorMarca(Long marcaId) {
        return zapatillaRepository.findByMarcaId(marcaId);
    }

    public List<Zapatilla> buscarPorEstado(String estado) {
        return zapatillaRepository.findByEstado(estado);
    }

    @Transactional
    public Zapatilla guardar(Zapatilla zapatilla) {
        return zapatillaRepository.save(zapatilla);
    }

    public void eliminar(Long id) {
        zapatillaRepository.deleteById(id);
    }
}