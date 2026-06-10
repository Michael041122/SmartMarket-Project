package com.rene.proveedores.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProveedorResponse {
    private Long id;
    private String nombre;
    private String empresa;
    private String email;
    private String telefono;
    private String direccion;
    private Boolean activo;

    public ProveedorResponse(Long id, String nombre, String empresa, String email,
                              String telefono, String direccion, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.empresa = empresa;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.activo = activo;
    }
}
