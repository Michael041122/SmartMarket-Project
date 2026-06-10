package com.rene.clientes.service;

import com.rene.clientes.dto.ClienteResponse;
import com.rene.clientes.entity.Cliente;
import java.util.List;

public interface ClienteService {
    ClienteResponse crearCliente(Cliente cliente);
    ClienteResponse obtenerCliente(Long id);
    List<ClienteResponse> obtenerTodosLosClientes();
    ClienteResponse actualizarCliente(Long id, Cliente cliente);
    void eliminarCliente(Long id);
    List<ClienteResponse> obtenerClientesActivos();
    ClienteResponse obtenerClientePorEmail(String email);
}
