package com.zapas.service_notificaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zapas.service_notificaciones.model.Notificacion;
import com.zapas.service_notificaciones.service.NotificacionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public List<Notificacion> listar() {
        return notificacionService.listarTodos();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Notificacion> listarPorUsuario(@PathVariable Long usuarioId) {
        return notificacionService.listarPorUsuario(usuarioId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> obtener(@PathVariable Long id) {
        Notificacion notificacion = notificacionService.obtenerConUsuario(id);
        if (notificacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notificacion);
    }

    @PostMapping
    public ResponseEntity<Notificacion> crear(@RequestBody Notificacion notificacion) {
        return ResponseEntity.ok(notificacionService.guardar(notificacion));
    }

    // Este marca como leido 
    @PatchMapping("/{id}/leida")
    public ResponseEntity<Notificacion> marcarLeida(@PathVariable Long id) {
        Notificacion notificacion = notificacionService.marcarComoLeida(id);
        if (notificacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notificacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        notificacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}