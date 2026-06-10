package com.rene.productos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor


public class ProductoResponse {
    private Long id;
    private String nombre;  // 👈 Asegúrate de tener este campo
    private Double precio;
    private Boolean disponible;

    // 👈 Tu constructor debe aceptar los 4 parámetros en este orden exacto:
    public ProductoResponse(Long id, String nombre, Double precio, Boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;
    }

    // Tus Getters y Setters...
}