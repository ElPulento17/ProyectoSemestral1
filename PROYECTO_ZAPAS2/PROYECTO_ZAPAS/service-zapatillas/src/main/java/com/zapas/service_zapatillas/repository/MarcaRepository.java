package com.zapas.service_zapatillas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zapas.service_zapatillas.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

    Marca findByNombre(String nombre);
}