package com.zapas.service_envios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zapas.service_envios.model.Envio;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {

    List<Envio> findByPedidoId(Long pedidoId);

    List<Envio> findByEstadoPaquete(String estadoPaquete);
}