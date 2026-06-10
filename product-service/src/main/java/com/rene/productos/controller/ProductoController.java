package com.rene.productos.controller;

import com.rene.productos.dto.ProductoResponse;
import com.rene.productos.entity.Producto; // O tu DTO ProductoRequest si lo usas
import com.rene.productos.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/productos")

@CrossOrigin(origins = "http://localhost:5173")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    // 1️⃣ ALTA: Crear un nuevo producto
    @PostMapping
    public ResponseEntity<ProductoResponse> crear(@RequestBody Producto producto) {
        ProductoResponse nuevoProducto = service.crearProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED); // Retorna 201 Created
    }

    // 2️⃣ CONSULTA: Obtener TODOS los productos
    @GetMapping
    public ResponseEntity<List<ProductoResponse>> obtenerTodos() {
        List<ProductoResponse> productos = service.obtenerTodosLosProductos();
        return ResponseEntity.ok(productos); // Retorna 200 OK
    }

    // 2️⃣ CONSULTA: Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtener(@PathVariable Long id) {
        ProductoResponse producto = service.obtenerProducto(id);
        return ResponseEntity.ok(producto); // Retorna 200 OK
    }

    // 3️⃣ EDICIÓN: Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        ProductoResponse productoActualizado = service.actualizarProducto(id, producto);
        return ResponseEntity.ok(productoActualizado); // Retorna 200 OK
    }

    // 4️⃣ ELIMINACIÓN: Borrar un producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarProducto(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }

    // 🔥 EXTRA: Tu endpoint de disponibilidad original
    @GetMapping("/{id}/disponibilidad")
    public ResponseEntity<Boolean> disponibilidad(@PathVariable Long id) {
        Boolean disp = service.obtenerDisponibilidad(id);
        return ResponseEntity.ok(disp);
    }
}