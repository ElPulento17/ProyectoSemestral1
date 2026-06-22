package com.zapas.service_usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zapas.service_usuarios.model.TipoRol;

@Repository
public interface TipoRolRepository extends JpaRepository<TipoRol, Long>{

    TipoRol findByNombre(String nombre);

    TipoRol findByNombreIgnoreCase(String nombre);

}
