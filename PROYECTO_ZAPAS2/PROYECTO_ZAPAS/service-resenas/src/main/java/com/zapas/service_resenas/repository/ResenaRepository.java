package com.zapas.service_resenas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zapas.service_resenas.model.Resena;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {

    List<Resena> findByZapatillaId(Long zapatillaId);

    List<Resena> findByUsuarioId(Long usuarioId);
}