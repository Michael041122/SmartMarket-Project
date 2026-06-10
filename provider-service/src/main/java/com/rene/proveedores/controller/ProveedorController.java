package com.rene.proveedores.controller;

import com.rene.proveedores.dto.ProveedorResponse;
import com.rene.proveedores.entity.Proveedor;
import com.rene.proveedores.service.ProveedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final ProveedorService service;

    public ProveedorController(ProveedorService service) {
        this.service = service;
    }

    // 1 ALTA: Crear nuevo proveedor
    @PostMapping
    public ResponseEntity<ProveedorResponse> crear(@RequestBody Proveedor proveedor) {
        ProveedorResponse nuevoProveedor = service.crearProveedor(proveedor);
        return new ResponseEntity<>(nuevoProveedor, HttpStatus.CREATED);
    }

    // 2 CONSULTA: Obtener todos los proveedores
    @GetMapping
    public ResponseEntity<List<ProveedorResponse>> obtenerTodos() {
        List<ProveedorResponse> proveedores = service.obtenerTodosLosProveedores();
        return ResponseEntity.ok(proveedores);
    }

    // 2 CONSULTA: Obtener proveedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponse> obtener(@PathVariable Long id) {
        ProveedorResponse proveedor = service.obtenerProveedor(id);
        return ResponseEntity.ok(proveedor);
    }

    // 3 EDICION: Actualizar proveedor
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponse> actualizar(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        ProveedorResponse proveedorActualizado = service.actualizarProveedor(id, proveedor);
        return ResponseEntity.ok(proveedorActualizado);
    }

    // 4 ELIMINACION: Borrar proveedor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }

    // EXTRA: Solo proveedores activos
    @GetMapping("/activos")
    public ResponseEntity<List<ProveedorResponse>> obtenerActivos() {
        List<ProveedorResponse> proveedores = service.obtenerProveedoresActivos();
        return ResponseEntity.ok(proveedores);
    }

    // EXTRA: Buscar por email
    @GetMapping("/email/{email}")
    public ResponseEntity<ProveedorResponse> obtenerPorEmail(@PathVariable String email) {
        ProveedorResponse proveedor = service.obtenerProveedorPorEmail(email);
        return ResponseEntity.ok(proveedor);
    }

    // EXTRA: Buscar por nombre de empresa
    @GetMapping("/empresa/{empresa}")
    public ResponseEntity<List<ProveedorResponse>> buscarPorEmpresa(@PathVariable String empresa) {
        List<ProveedorResponse> proveedores = service.buscarPorEmpresa(empresa);
        return ResponseEntity.ok(proveedores);
    }
}
