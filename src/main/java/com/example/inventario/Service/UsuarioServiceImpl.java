package com.example.inventario.Service;

import com.example.inventario.Dto.UsuarioDTO;
import com.example.inventario.Entity.Usuario;
import com.example.inventario.Repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<UsuarioDTO> listarClientes() {
        List<Usuario> usuarios = clienteRepository.findAll();
        return usuarios.stream()
                .map(this::convertirAClienteDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Usuario crearCliente(Usuario usuario) {
        return clienteRepository.save(usuario);
    }

    @Override
    public UsuarioDTO actualizarCliente(Long id, Usuario usuario) {
        if (clienteRepository.existsById(id)) {
            usuario.setId(id);
            Usuario usuarioActualizado = clienteRepository.save(usuario);
            return convertirAClienteDTO(usuarioActualizado);
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
    public UsuarioDTO buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .map(this::convertirAClienteDTO)
                .orElse(null); // O lanza una excepción si no se encuentra el cliente
    }

    @Override
    public UsuarioDTO buscarClientePorEmail(String email) {
        return clienteRepository.findByEmail(email)
                .map(this::convertirAClienteDTO)
                .orElse(null); // O lanza una excepción si no se encuentra el cliente
    }

    @Override
    public UsuarioDTO convertirAClienteDTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }
}
