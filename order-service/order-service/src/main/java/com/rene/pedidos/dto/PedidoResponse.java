package com.rene.pedidos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PedidoResponse {
    private Long id;
    private String cliente;
    private Long productoId;
    private Integer cantidad;
    private String estado;

    public PedidoResponse(Long id, String cliente, Long productoId, Integer cantidad, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.estado = estado;
    }
}
