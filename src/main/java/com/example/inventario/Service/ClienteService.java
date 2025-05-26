package com.example.inventario.Service;

import com.example.inventario.Dto.ClienteDTO;
import com.example.inventario.Entity.Cliente;
import jakarta.validation.constraints.NotBlank;


import java.util.List;

public interface ClienteService {
    List<ClienteDTO> listarClientes();
    Cliente crearCliente(Cliente cliente);
    ClienteDTO actualizarCliente(Long id, Cliente cliente);
    void eliminarCliente(@NotBlank Long id);
    ClienteDTO buscarClientePorId(@NotBlank Long id);
    ClienteDTO buscarClientePorEmail(@NotBlank String email);
    ClienteDTO convertirAClienteDTO(Cliente cliente);
}