package com.rene.proveedores.service.impl;

import com.rene.proveedores.dto.ProveedorResponse;
import com.rene.proveedores.entity.Proveedor;
import com.rene.proveedores.repository.ProveedorRepository;
import com.rene.proveedores.service.ProveedorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository repository;

    public ProveedorServiceImpl(ProveedorRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProveedorResponse crearProveedor(Proveedor proveedor) {
        Proveedor nuevoProveedor = repository.save(proveedor);
        return mapearADto(nuevoProveedor);
    }

    @Override
    public ProveedorResponse obtenerProveedor(Long id) {
        Proveedor proveedor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con el ID: " + id));
        return mapearADto(proveedor);
    }

    @Override
    public List<ProveedorResponse> obtenerTodosLosProveedores() {
        return repository.findAll()
                .stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    @Override
    public ProveedorResponse actualizarProveedor(Long id, Proveedor proveedorDetalles) {
        Proveedor proveedorExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede actualizar. Proveedor no encontrado con ID: " + id));

        proveedorExistente.setNombre(proveedorDetalles.getNombre());
        proveedorExistente.setEmpresa(proveedorDetalles.getEmpresa());
        proveedorExistente.setEmail(proveedorDetalles.getEmail());
        proveedorExistente.setTelefono(proveedorDetalles.getTelefono());
        proveedorExistente.setDireccion(proveedorDetalles.getDireccion());
        proveedorExistente.setActivo(proveedorDetalles.getActivo());

        Proveedor proveedorActualizado = repository.save(proveedorExistente);
        return mapearADto(proveedorActualizado);
    }

    @Override
    public void eliminarProveedor(Long id) {
        Proveedor proveedor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar. Proveedor no encontrado con ID: " + id));
        repository.delete(proveedor);
    }

    @Override
    public List<ProveedorResponse> obtenerProveedoresActivos() {
        return repository.findByActivo(true)
                .stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    @Override
    public ProveedorResponse obtenerProveedorPorEmail(String email) {
        Proveedor proveedor = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con email: " + email));
        return mapearADto(proveedor);
    }

    @Override
    public List<ProveedorResponse> buscarPorEmpresa(String empresa) {
        return repository.findByEmpresaContainingIgnoreCase(empresa)
                .stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    private ProveedorResponse mapearADto(Proveedor proveedor) {
        return new ProveedorResponse(
                proveedor.getId(),
                proveedor.getNombre(),
                proveedor.getEmpresa(),
                proveedor.getEmail(),
                proveedor.getTelefono(),
                proveedor.getDireccion(),
                proveedor.getActivo()
        );
    }
}
