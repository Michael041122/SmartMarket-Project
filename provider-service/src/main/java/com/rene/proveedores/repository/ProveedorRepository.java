package com.rene.proveedores.repository;

import com.rene.proveedores.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    List<Proveedor> findByActivo(Boolean activo);
    Optional<Proveedor> findByEmail(String email);
    List<Proveedor> findByEmpresaContainingIgnoreCase(String empresa);
}
