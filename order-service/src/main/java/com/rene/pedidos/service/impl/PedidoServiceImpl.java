package com.rene.pedidos.service.impl;

import com.rene.pedidos.dto.PedidoResponse;
import com.rene.pedidos.entity.Pedido;
import com.rene.pedidos.repository.PedidoRepository;
import com.rene.pedidos.service.PedidoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;

    public PedidoServiceImpl(PedidoRepository repository) {
        this.repository = repository;
    }

    @Override
    public PedidoResponse crearPedido(Pedido pedido) {
        Pedido nuevoPedido = repository.save(pedido);
        return mapearADto(nuevoPedido);
    }

    @Override
    public PedidoResponse obtenerPedido(Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con el ID: " + id));
        return mapearADto(pedido);
    }

    @Override
    public List<PedidoResponse> obtenerTodosLosPedidos() {
        return repository.findAll()
                .stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    @Override
    public PedidoResponse actualizarPedido(Long id, Pedido pedidoDetalles) {
        Pedido pedidoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede actualizar. Pedido no encontrado con ID: " + id));

        pedidoExistente.setCliente(pedidoDetalles.getCliente());
        pedidoExistente.setProductoId(pedidoDetalles.getProductoId());
        pedidoExistente.setCantidad(pedidoDetalles.getCantidad());
        pedidoExistente.setEstado(pedidoDetalles.getEstado());

        Pedido pedidoActualizado = repository.save(pedidoExistente);
        return mapearADto(pedidoActualizado);
    }

    @Override
    public void eliminarPedido(Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar. Pedido no encontrado con ID: " + id));
        repository.delete(pedido);
    }

    @Override
    public List<PedidoResponse> obtenerPedidosPorCliente(String cliente) {
        return repository.findByCliente(cliente)
                .stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    private PedidoResponse mapearADto(Pedido pedido) {
        return new PedidoResponse(
                pedido.getId(),
                pedido.getCliente(),
                pedido.getProductoId(),
                pedido.getCantidad(),
                pedido.getEstado()
        );
    }
}
