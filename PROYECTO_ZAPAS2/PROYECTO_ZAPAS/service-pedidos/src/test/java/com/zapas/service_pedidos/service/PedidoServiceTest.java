package com.zapas.service_pedidos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zapas.service_pedidos.model.Pedido;
import com.zapas.service_pedidos.repository.PedidoRepository;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    public void guardarPedidoTest() {

        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setUsuarioId(1L);
        pedido.setTotal(85000);

        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Pedido resultado = pedidoService.guardarPedido(pedido);

        assertNotNull(resultado);
        assertEquals(85000, resultado.getTotal());
        assertEquals(1L, resultado.getUsuarioId());
    }

    @Test
    public void listarTodosTest() {

        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setTotal(50000);

        when(pedidoRepository.findAll()).thenReturn(java.util.List.of(pedido));

        java.util.List<Pedido> resultado = pedidoService.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }
}