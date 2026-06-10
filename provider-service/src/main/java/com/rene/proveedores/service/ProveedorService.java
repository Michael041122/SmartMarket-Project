package com.rene.proveedores.service;

import com.rene.proveedores.dto.ProveedorResponse;
import com.rene.proveedores.entity.Proveedor;
import java.util.List;

public interface ProveedorService {
    ProveedorResponse crearProveedor(Proveedor proveedor);
    ProveedorResponse obtenerProveedor(Long id);
    List<ProveedorResponse> obtenerTodosLosProveedores();
    ProveedorResponse actualizarProveedor(Long id, Proveedor proveedor);
    void eliminarProveedor(Long id);
    List<ProveedorResponse> obtenerProveedoresActivos();
    ProveedorResponse obtenerProveedorPorEmail(String email);
    List<ProveedorResponse> buscarPorEmpresa(String empresa);
}
