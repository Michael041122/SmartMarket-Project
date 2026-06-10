package com.rene.ventas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VentaResponse {
    private Long id;
    private String cliente;
    private Long productoId;
    private Integer cantidad;
    private Double total;
    private String fecha;

    public VentaResponse(Long id, String cliente, Long productoId, Integer cantidad, Double total, String fecha) {
        this.id = id;
        this.cliente = cliente;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.total = total;
        this.fecha = fecha;
    }
}
