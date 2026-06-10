package com.rene.clientes.controller;

import com.rene.clientes.dto.ClienteResponse;
import com.rene.clientes.entity.Cliente;
import com.rene.clientes.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    // 1 ALTA: Crear nuevo cliente
    @PostMapping
    public ResponseEntity<ClienteResponse> crear(@RequestBody Cliente cliente) {
        ClienteResponse nuevoCliente = service.crearCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    // 2 CONSULTA: Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<ClienteResponse>> obtenerTodos() {
        List<ClienteResponse> clientes = service.obtenerTodosLosClientes();
        return ResponseEntity.ok(clientes);
    }

    // 2 CONSULTA: Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> obtener(@PathVariable Long id) {
        ClienteResponse cliente = service.obtenerCliente(id);
        return ResponseEntity.ok(cliente);
    }

    // 3 EDICION: Actualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> actualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        ClienteResponse clienteActualizado = service.actualizarCliente(id, cliente);
        return ResponseEntity.ok(clienteActualizado);
    }

    // 4 ELIMINACION: Borrar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    // EXTRA: Obtener solo clientes activos
    @GetMapping("/activos")
    public ResponseEntity<List<ClienteResponse>> obtenerActivos() {
        List<ClienteResponse> clientes = service.obtenerClientesActivos();
        return ResponseEntity.ok(clientes);
    }

    // EXTRA: Buscar cliente por email
    @GetMapping("/email/{email}")
    public ResponseEntity<ClienteResponse> obtenerPorEmail(@PathVariable String email) {
        ClienteResponse cliente = service.obtenerClientePorEmail(email);
        return ResponseEntity.ok(cliente);
    }
}
