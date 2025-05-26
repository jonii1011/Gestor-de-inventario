package com.example.inventario.Service;

import com.example.inventario.Dto.ClienteDTO;
import com.example.inventario.Entity.Cliente;
import com.example.inventario.Repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteServiceImpl implements ClienteService{
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteDTO> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(this::convertirAClienteDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public ClienteDTO actualizarCliente(Long id, Cliente cliente) {
        if (clienteRepository.existsById(id)) {
            cliente.setId(id);
            Cliente clienteActualizado = clienteRepository.save(cliente);
            return convertirAClienteDTO(clienteActualizado);
        }
        return null;
    }

    @Override
    public void eliminarCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else{
            new EntityNotFoundException("Cliente no encontrado con ID: " + id);
        }
    }

    @Override
    public ClienteDTO buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .map(this::convertirAClienteDTO)
                .orElse(null); // O lanza una excepción si no se encuentra el cliente
    }

    @Override
    public ClienteDTO buscarClientePorEmail(String email) {
        return clienteRepository.findByEmail(email)
                .map(this::convertirAClienteDTO)
                .orElse(null); // O lanza una excepción si no se encuentra el cliente
    }

    @Override
    public ClienteDTO convertirAClienteDTO(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDTO.class);
    }
}
