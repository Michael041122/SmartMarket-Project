package com.rene.pedidos.service;

import com.rene.pedidos.dto.PedidoResponse;
import com.rene.pedidos.entity.Pedido;
import java.util.List;

public interface PedidoService {
    PedidoResponse crearPedido(Pedido pedido);
    PedidoResponse obtenerPedido(Long id);
    List<PedidoResponse> obtenerTodosLosPedidos();
    PedidoResponse actualizarPedido(Long id, Pedido pedido);
    void eliminarPedido(Long id);
    List<PedidoResponse> obtenerPedidosPorCliente(String cliente);
}
