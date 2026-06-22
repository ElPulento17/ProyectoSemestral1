package com.zapas.service_pedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zapas.service_pedidos.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByUsuarioId(Long usuarioId);

    List<Pedido> findByEstadoPedido(String estadoPedido);

    @Query("""
            SELECT p FROM Pedido p
            JOIN FETCH p.detalles d
            WHERE p.usuarioId = :usuarioId
            """)
    List<Pedido> findPedidosConDetallesPorUsuario(Long usuarioId);
}