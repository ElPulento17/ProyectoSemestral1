package com.zapas.service_descuentos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zapas.service_descuentos.model.Descuento;

@Repository
public interface DescuentoRepository extends JpaRepository<Descuento, Long> {

    Optional<Descuento> findByCodigo(String codigo);

    List<Descuento> findByActivo(boolean activo);
}