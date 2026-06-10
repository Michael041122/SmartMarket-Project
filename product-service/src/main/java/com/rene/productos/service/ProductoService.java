package com.rene.productos.service;

import com.rene.productos.dto.ProductoResponse;
import com.rene.productos.entity.Producto;
import java.util.List;

public interface ProductoService {
    ProductoResponse crearProducto(Producto producto);
    ProductoResponse obtenerProducto(Long id);
    List<ProductoResponse> obtenerTodosLosProductos();
    ProductoResponse actualizarProducto(Long id, Producto producto);
    void eliminarProducto(Long id);
    Boolean obtenerDisponibilidad(Long id);
}