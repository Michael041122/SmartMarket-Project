package com.rene.ventas.service;

import com.rene.ventas.dto.VentaResponse;
import com.rene.ventas.entity.Venta;
import java.util.List;

public interface VentaService {
    VentaResponse crearVenta(Venta venta);
    VentaResponse obtenerVenta(Long id);
    List<VentaResponse> obtenerTodasLasVentas();
    VentaResponse actualizarVenta(Long id, Venta venta);
    void eliminarVenta(Long id);
    List<VentaResponse> obtenerVentasPorCliente(String cliente);
    List<VentaResponse> obtenerVentasPorProducto(Long productoId);
}
