package com.rene.clientes.service.impl;

import com.rene.clientes.dto.ClienteResponse;
import com.rene.clientes.entity.Cliente;
import com.rene.clientes.repository.ClienteRepository;
import com.rene.clientes.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClienteResponse crearCliente(Cliente cliente) {
        Cliente nuevoCliente = repository.save(cliente);
        return mapearADto(nuevoCliente);
    }

    @Override
    public ClienteResponse obtenerCliente(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con el ID: " + id));
        return mapearADto(cliente);
    }

    @Override
    public List<ClienteResponse> obtenerTodosLosClientes() {
        return repository.findAll()
                .stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteResponse actualizarCliente(Long id, Cliente clienteDetalles) {
        Cliente clienteExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede actualizar. Cliente no encontrado con ID: " + id));

        clienteExistente.setNombre(clienteDetalles.getNombre());
        clienteExistente.setApellido(clienteDetalles.getApellido());
        clienteExistente.setEmail(clienteDetalles.getEmail());
        clienteExistente.setTelefono(clienteDetalles.getTelefono());
        clienteExistente.setDireccion(clienteDetalles.getDireccion());
        clienteExistente.setActivo(clienteDetalles.getActivo());

        Cliente clienteActualizado = repository.save(clienteExistente);
        return mapearADto(clienteActualizado);
    }

    @Override
    public void eliminarCliente(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar. Cliente no encontrado con ID: " + id));
        repository.delete(cliente);
    }

    @Override
    public List<ClienteResponse> obtenerClientesActivos() {
        return repository.findByActivo(true)
                .stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteResponse obtenerClientePorEmail(String email) {
        Cliente cliente = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con email: " + email));
        return mapearADto(cliente);
    }

    private ClienteResponse mapearADto(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getEmail(),
                cliente.getTelefono(),
                cliente.getDireccion(),
                cliente.getActivo()
        );
    }
}
