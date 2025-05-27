package com.example.inventario.Controller;

import com.example.inventario.Dto.UsuarioDTO;
import com.example.inventario.Entity.Usuario;
import com.example.inventario.Service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Listar todos los clientes", description = "Devuelve una lista de todos los clientes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes recuperada con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarProductos() {
        List<UsuarioDTO> clientes = usuarioService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Crear un nuevo cliente", description = "Crea un nuevo cliente y lo almacena en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping
    public ResponseEntity<UsuarioDTO> crearCliente(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.crearCliente(usuario);
        UsuarioDTO usuarioDTO = usuarioService.convertirAClienteDTO(nuevoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
    }

    @Operation(summary = "Actualizar un cliente existente", description = "Actualiza un cliente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarCliente(@Parameter(description = "ID del cliente a actualizar") @PathVariable Long id, @RequestBody Usuario usuario) {
        UsuarioDTO usuarioDTO = usuarioService.actualizarCliente(id, usuario);
        if (usuarioDTO != null) {
            return ResponseEntity.ok(usuarioDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@Parameter(description = "ID del cliente a eliminar") @PathVariable Long id) {
        try {
            usuarioService.eliminarCliente(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Buscar un cliente por ID", description = "Devuelve un cliente específico por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarClientePorId(@Parameter(description = "ID del cliente a buscar") @PathVariable Long id) {
        UsuarioDTO usuarioDTO = usuarioService.buscarClientePorId(id);
        if (usuarioDTO != null) {
            return ResponseEntity.ok(usuarioDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
