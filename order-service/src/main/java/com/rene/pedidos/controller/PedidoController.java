package com.rene.pedidos.controller;

import com.rene.pedidos.dto.PedidoResponse;
import com.rene.pedidos.entity.Pedido;
import com.rene.pedidos.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    // 1 ALTA: Crear nuevo pedido
    @PostMapping
    public ResponseEntity<PedidoResponse> crear(@RequestBody Pedido pedido) {
        PedidoResponse nuevoPedido = service.crearPedido(pedido);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    // 2 CONSULTA: Obtener todos los pedidos
    @GetMapping
    public ResponseEntity<List<PedidoResponse>> obtenerTodos() {
        List<PedidoResponse> pedidos = service.obtenerTodosLosPedidos();
        return ResponseEntity.ok(pedidos);
    }

    // 2 CONSULTA: Obtener pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> obtener(@PathVariable Long id) {
        PedidoResponse pedido = service.obtenerPedido(id);
        return ResponseEntity.ok(pedido);
    }

    // 3 EDICION: Actualizar pedido
    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponse> actualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        PedidoResponse pedidoActualizado = service.actualizarPedido(id, pedido);
        return ResponseEntity.ok(pedidoActualizado);
    }

    // 4 ELIMINACION: Borrar pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }

    // EXTRA: Buscar por cliente
    @GetMapping("/cliente/{cliente}")
    public ResponseEntity<List<PedidoResponse>> obtenerPorCliente(@PathVariable String cliente) {
        List<PedidoResponse> pedidos = service.obtenerPedidosPorCliente(cliente);
        return ResponseEntity.ok(pedidos);
    }
}
