package com.zapas.service_pedidos.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.zapas.service_pedidos.model.Pedido;
import com.zapas.service_pedidos.model.DetallePedido;
import com.zapas.service_pedidos.repository.PedidoRepository;
import jakarta.transaction.Transactional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Transactional
    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido obtenerPedidoCompleto(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        
        if (pedido != null) {

            if (pedido.getUsuarioId() != null) {
                try {
                    Object usuario = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8081/api/v1/Usuario/" + pedido.getUsuarioId())
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block();
                    pedido.setDatosUsuario(usuario);
                } catch (Exception e) {
                    pedido.setDatosUsuario("Información de usuario no disponible");
                }
            }

            if (pedido.getDetalles() != null) {
                for (DetallePedido detalle : pedido.getDetalles()) {
                    if (detalle.getZapatillaId() != null) {
                        try {
                            Object zapatilla = webClientBuilder.build()
                                .get()
                                .uri("http://localhost:8082/api/v1/zapatillas/" + detalle.getZapatillaId())
                                .retrieve()
                                .bodyToMono(Object.class)
                                .block();
                            detalle.setDatosZapatilla(zapatilla);
                        } catch (Exception e) {
                            detalle.setDatosZapatilla("Información de zapatilla no disponible");
                        }
                    }
                }
            }
        }
        return pedido;
    }
}