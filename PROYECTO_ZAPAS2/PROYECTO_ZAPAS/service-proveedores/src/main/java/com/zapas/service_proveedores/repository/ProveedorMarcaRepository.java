package com.zapas.service_proveedores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zapas.service_proveedores.model.ProveedorMarca;

@Repository
public interface ProveedorMarcaRepository extends JpaRepository<ProveedorMarca, Long> {

    List<ProveedorMarca> findByProveedorId(Long proveedorId);
}