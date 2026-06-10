package com.rene.ventas.service.impl;

import com.rene.ventas.dto.VentaResponse;
import com.rene.ventas.entity.Venta;
import com.rene.ventas.repository.VentaRepository;
import com.rene.ventas.service.VentaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository repository;

    public VentaServiceImpl(VentaRepository repository) {
        this.repository = repository;
    }

    @Override
    public VentaResponse crearVenta(Venta venta) {
        Venta nuevaVenta = repository.save(venta);
        return mapearADto(nuevaVenta);
    }

    @Override
    public VentaResponse obtenerVenta(Long id) {
        Venta venta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con el ID: " + id));
        return mapearADto(venta);
    }

    @Override
    public List<VentaResponse> obtenerTodasLasVentas() {
        return repository.findAll()
                .stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    @Override
    public VentaResponse actualizarVenta(Long id, Venta ventaDetalles) {
        Venta ventaExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede actualizar. Venta no encontrada con ID: " + id));

        ventaExistente.setCliente(ventaDetalles.getCliente());
        ventaExistente.setProductoId(ventaDetalles.getProductoId());
        ventaExistente.setCantidad(ventaDetalles.getCantidad());
        ventaExistente.setTotal(ventaDetalles.getTotal());
        ventaExistente.setFecha(ventaDetalles.getFecha());

        Venta ventaActualizada = repository.save(ventaExistente);
        return mapearADto(ventaActualizada);
    }

    @Override
    public void eliminarVenta(Long id) {
        Venta venta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar. Venta no encontrada con ID: " + id));
        repository.delete(venta);
    }

    @Override
    public List<VentaResponse> obtenerVentasPorCliente(String cliente) {
        return repository.findByCliente(cliente)
                .stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VentaResponse> obtenerVentasPorProducto(Long productoId) {
        return repository.findByProductoId(productoId)
                .stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    private VentaResponse mapearADto(Venta venta) {
        return new VentaResponse(
                venta.getId(),
                venta.getCliente(),
                venta.getProductoId(),
                venta.getCantidad(),
                venta.getTotal(),
                venta.getFecha()
        );
    }
}
