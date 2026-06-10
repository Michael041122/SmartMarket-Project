package com.rene.productos.service.impl;

import com.rene.productos.dto.ProductoResponse;
import com.rene.productos.entity.Producto;
import com.rene.productos.repository.ProductoRepository;
import com.rene.productos.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repository;

    public ProductoServiceImpl(ProductoRepository repository) {
        this.repository = repository;
    }

    // 1️⃣ ALTA
    @Override
    public ProductoResponse crearProducto(Producto producto) {
        // Guardamos la entidad en la base de datos
        Producto nuevoProducto = repository.save(producto);
        // La mapeamos al DTO de respuesta utilizando tu constructor
        return mapearADto(nuevoProducto);
    }

    // 2️⃣ CONSULTA (Individual)
    @Override
    public ProductoResponse obtenerProducto(Long id) {
        Producto producto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));
        return mapearADto(producto);
    }

    // 2️⃣ CONSULTA (General)
    @Override
    public List<ProductoResponse> obtenerTodosLosProductos() {
        List<Producto> productos = repository.findAll();
        // Convertimos la lista de entidades a una lista de DTOs
        return productos.stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    // 3️⃣ EDICIÓN
    @Override
    public ProductoResponse actualizarProducto(Long id, Producto productoDetalles) {
        // Buscamos si existe el producto original
        Producto productoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede actualizar. Producto no encontrado con el ID: " + id));

        // Actualizamos los campos con los nuevos datos que llegan del controlador
        productoExistente.setNombre(productoDetalles.getNombre());
        productoExistente.setPrecio(productoDetalles.getPrecio());
        productoExistente.setDisponible(productoDetalles.getDisponible());

        // Guardamos los cambios
        Producto productoActualizado = repository.save(productoExistente);
        return mapearADto(productoActualizado);
    }

    // 4️⃣ ELIMINACIÓN
    @Override
    public void eliminarProducto(Long id) {
        Producto producto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar. Producto no encontrado con el ID: " + id));
        repository.delete(producto);
    }

    // 🔥 Tu método original de disponibilidad
    @Override
    public Boolean obtenerDisponibilidad(Long id) {
        return repository.findById(id)
                .map(Producto::getDisponible)
                .orElse(false);
    }

    // 🛠️ Método auxiliar privado para no repetir código de mapeo (Entity -> DTO)
    private ProductoResponse mapearADto(Producto producto) {
        return new ProductoResponse(
                producto.getId(),
                producto.getNombre(), // ⚠️ Asegúrate de que tu ProductoResponse tenga este campo en su constructor si lo necesitas en el frontend
                producto.getPrecio(),
                producto.getDisponible()
        );
    }
}