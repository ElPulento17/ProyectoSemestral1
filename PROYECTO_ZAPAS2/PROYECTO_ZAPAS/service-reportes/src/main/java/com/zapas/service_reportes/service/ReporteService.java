package com.zapas.service_reportes.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.zapas.service_reportes.model.Reporte;
import com.zapas.service_reportes.repository.ReporteRepository;

import jakarta.transaction.Transactional;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<Reporte> listarTodos() {
        return reporteRepository.findAll();
    }

    // este metodo es paraa generar un reporte nuevo consultandole los pedidos a service pedidos
    @Transactional
    public Reporte generarReporteVentas() {
        Reporte reporte = new Reporte();
        reporte.setTipo("VENTAS_GENERAL");
        reporte.setFechaGeneracion(LocalDate.now().toString());

        try {
            // aca me trae todos los pedidos desde el service pedidoes
            List<Map> pedidos = webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/api/v1/pedidos")
                .retrieve()
                .bodyToFlux(Map.class)
                .collectList()
                .block();

            int totalVentas = 0;
            int totalPedidos = 0;

            if (pedidos != null) {
                totalPedidos = pedidos.size();
                for (Map pedido : pedidos) {
                    Object total = pedido.get("total");
                    if (total != null) {
                        totalVentas += (Integer) total;
                    }
                }
            }

            reporte.setTotalVentas(totalVentas);
            reporte.setTotalPedidos(totalPedidos);

        } catch (Exception e) {
            // esto me sirve para ver si el micro de pedidos se cae me sirve igual para guardaer el reporte en 0
            reporte.setTotalVentas(0);
            reporte.setTotalPedidos(0);
        }

        return reporteRepository.save(reporte);
    }
}