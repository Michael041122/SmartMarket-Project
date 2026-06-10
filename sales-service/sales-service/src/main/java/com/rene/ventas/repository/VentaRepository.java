package com.rene.ventas.repository;

import com.rene.ventas.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByCliente(String cliente);
    List<Venta> findByProductoId(Long productoId);
}
