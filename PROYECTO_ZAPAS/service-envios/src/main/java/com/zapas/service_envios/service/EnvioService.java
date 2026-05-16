package com.zapas.service_envios.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.zapas.service_envios.model.Envio;
import com.zapas.service_envios.repository.EnvioRepository;
import jakarta.transaction.Transactional;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Transactional
    public Envio generarEnvio(Envio envio) {
        envio.setEstadoPaquete("PREPARANDO");
        return envioRepository.save(envio);
    }

    public List<Envio> listarTodos() {
        return envioRepository.findAll();
    }

    public Envio obtenerTrackingCompleto(Long id) {
        Envio envio = envioRepository.findById(id).orElse(null);
        
        if (envio != null && envio.getPedidoId() != null) {
            try {
                Object pedido = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8083/api/v1/pedidos/" + envio.getPedidoId())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
                envio.setDatosPedido(pedido);
            } catch (Exception e) {
                envio.setDatosPedido("La información del pedido no está disponible en este momento");
            }
        }
        return envio;
    }
}