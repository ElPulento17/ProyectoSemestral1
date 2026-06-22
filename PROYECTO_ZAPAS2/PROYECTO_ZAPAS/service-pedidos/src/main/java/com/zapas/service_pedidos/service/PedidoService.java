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

    public List<Pedido> listarPorUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }

    @Transactional
    public Pedido actualizarEstado(Long id, String nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido != null) {
            pedido.setEstadoPedido(nuevoEstado);
            return pedidoRepository.save(pedido);
        }
        return null;
    }

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
                        .uri("http://localhost:8081/api/v1/usuario/" + pedido.getUsuarioId())
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
                    if (detalle.getStockZapatillaId() != null) { 
                        try {
                            Object zapatilla = webClientBuilder.build()
                                .get()
                                .uri("http://localhost:8082/api/v1/zapatillas/stock/" + detalle.getStockZapatillaId())
                                .retrieve()
                                .bodyToMono(Object.class)
                                .block();
                            detalle.setDatosStock(zapatilla);
                        } catch (Exception e) {
                            detalle.setDatosStock("Información de zapatilla no disponible");
                        }
                    }
                }
            }
        }
        return pedido;
    }
}