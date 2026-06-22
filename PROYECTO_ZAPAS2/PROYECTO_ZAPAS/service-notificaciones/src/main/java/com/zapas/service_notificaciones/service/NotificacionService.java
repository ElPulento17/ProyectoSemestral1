package com.zapas.service_notificaciones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.zapas.service_notificaciones.model.Notificacion;
import com.zapas.service_notificaciones.repository.NotificacionRepository;

import jakarta.transaction.Transactional;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Transactional
    public Notificacion guardar(Notificacion notificacion) {
        notificacion.setLeida(false); // siempre empieza como no leída
        return notificacionRepository.save(notificacion);
    }

    public List<Notificacion> listarTodos() {
        return notificacionRepository.findAll();
    }

    public List<Notificacion> listarPorUsuario(Long usuarioId) {
        return notificacionRepository.findByUsuarioId(usuarioId);
    }

    public Notificacion obtenerConUsuario(Long id) {
        Notificacion notificacion = notificacionRepository.findById(id).orElse(null);

        if (notificacion != null && notificacion.getUsuarioId() != null) {
            try {
                Object usuario = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/api/v1/usuario/" + notificacion.getUsuarioId())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
                notificacion.setDatosUsuario(usuario);
            } catch (Exception e) {
                notificacion.setDatosUsuario("La informacion del usuario no esta disponible");
            }
        }
        return notificacion;
    }

    @Transactional
    public Notificacion marcarComoLeida(Long id) {
        Notificacion notificacion = notificacionRepository.findById(id).orElse(null);
        if (notificacion != null) {
            notificacion.setLeida(true);
            return notificacionRepository.save(notificacion);
        }
        return null;
    }

    public void eliminar(Long id) {
        notificacionRepository.deleteById(id);
    }
}