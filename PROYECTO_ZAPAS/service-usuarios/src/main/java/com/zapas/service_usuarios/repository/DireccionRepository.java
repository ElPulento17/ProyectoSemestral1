package com.zapas.service_usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zapas.service_usuarios.model.Direccion;
import com.zapas.service_usuarios.model.Usuario;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {

    Direccion findByUsuario(Usuario usuario);

    Direccion findByUsuarioId(Long usuarioId);
}
