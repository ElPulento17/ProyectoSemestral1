package com.zapas.service_resenas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.zapas.service_resenas.model.Resena;
import com.zapas.service_resenas.repository.ResenaRepository;

import jakarta.transaction.Transactional;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Transactional
    public Resena guardar(Resena resena) {
        return resenaRepository.save(resena);
    }

    public List<Resena> listarTodas() {
        return resenaRepository.findAll();
    }

    public List<Resena> listarPorZapatilla(Long zapatillaId) {
        return resenaRepository.findByZapatillaId(zapatillaId);
    }

    public Resena obtenerResenaCompleta(Long id) {
        Resena resena = resenaRepository.findById(id).orElse(null);

        if (resena != null) {

            if (resena.getUsuarioId() != null) {
                try {
                    Object usuario = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8081/api/v1/usuario/" + resena.getUsuarioId())
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block();
                    resena.setDatosUsuario(usuario);
                } catch (Exception e) {
                    resena.setDatosUsuario("Información de usuario no disponible");
                }
            }

            if (resena.getZapatillaId() != null) {
                try {
                    Object zapatilla = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8082/api/v1/zapatillas/" + resena.getZapatillaId())
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block();
                    resena.setDatosZapatilla(zapatilla);
                } catch (Exception e) {
                    resena.setDatosZapatilla("Información de zapatilla no disponible");
                }
            }
        }
        return resena;
    }

    public void eliminar(Long id) {
        resenaRepository.deleteById(id);
    }
}