package com.example.inventario.Service;

import com.example.inventario.Dto.UsuarioDTO;
import com.example.inventario.Entity.Usuario;
import jakarta.validation.constraints.NotBlank;


import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> listarClientes();
    Usuario crearCliente(Usuario usuario);
    UsuarioDTO actualizarCliente(Long id, Usuario usuario);
    void eliminarCliente(@NotBlank Long id);
    UsuarioDTO buscarClientePorId(@NotBlank Long id);
    UsuarioDTO buscarClientePorEmail(@NotBlank String email);
    UsuarioDTO convertirAClienteDTO(Usuario usuario);
}