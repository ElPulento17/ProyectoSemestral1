package com.zapas.service_pagos.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.zapas.service_pagos.model.Pago;
import com.zapas.service_pagos.repository.PagoRepository;
import jakarta.transaction.Transactional;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Transactional
    public Pago procesarPago(Pago pago) {
        pago.setEstado("APROBADO");
        pago.setFechaPago(LocalDate.now());
        return pagoRepository.save(pago);
    }

    public List<Pago> listarTodos() {
        return pagoRepository.findAll();
    }

    public List<Pago> listarPorPedido(Long pedidoId) {
        return pagoRepository.findByPedidoId(pedidoId);
    }

    public Pago obtenerComprobanteCompleto(Long id) {
        Pago pago = pagoRepository.findById(id).orElse(null);

        if (pago != null && pago.getPedidoId() != null) {
            try {
                Object pedido = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8083/api/v1/pedidos/" + pago.getPedidoId())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
                pago.setDatosPedido(pedido);
            } catch (Exception e) {
                pago.setDatosPedido("Información del pedido no disponible");
            }
        }
        return pago;
    }
}