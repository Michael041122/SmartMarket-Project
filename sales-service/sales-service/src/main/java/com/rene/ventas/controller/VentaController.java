package com.rene.ventas.controller;

import com.rene.ventas.dto.VentaResponse;
import com.rene.ventas.entity.Venta;
import com.rene.ventas.service.VentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService service;

    public VentaController(VentaService service) {
        this.service = service;
    }

    // 1 ALTA: Crear nueva venta
    @PostMapping
    public ResponseEntity<VentaResponse> crear(@RequestBody Venta venta) {
        VentaResponse nuevaVenta = service.crearVenta(venta);
        return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
    }

    // 2 CONSULTA: Obtener todas las ventas
    @GetMapping
    public ResponseEntity<List<VentaResponse>> obtenerTodas() {
        List<VentaResponse> ventas = service.obtenerTodasLasVentas();
        return ResponseEntity.ok(ventas);
    }

    // 2 CONSULTA: Obtener venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<VentaResponse> obtener(@PathVariable Long id) {
        VentaResponse venta = service.obtenerVenta(id);
        return ResponseEntity.ok(venta);
    }

    // 3 EDICION: Actualizar venta
    @PutMapping("/{id}")
    public ResponseEntity<VentaResponse> actualizar(@PathVariable Long id, @RequestBody Venta venta) {
        VentaResponse ventaActualizada = service.actualizarVenta(id, venta);
        return ResponseEntity.ok(ventaActualizada);
    }

    // 4 ELIMINACION: Borrar venta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }

    // EXTRA: Buscar por cliente
    @GetMapping("/cliente/{cliente}")
    public ResponseEntity<List<VentaResponse>> obtenerPorCliente(@PathVariable String cliente) {
        List<VentaResponse> ventas = service.obtenerVentasPorCliente(cliente);
        return ResponseEntity.ok(ventas);
    }

    // EXTRA: Buscar por producto
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<VentaResponse>> obtenerPorProducto(@PathVariable Long productoId) {
        List<VentaResponse> ventas = service.obtenerVentasPorProducto(productoId);
        return ResponseEntity.ok(ventas);
    }
}
