package com.zapas.service_reportes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zapas.service_reportes.model.Reporte;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {

}