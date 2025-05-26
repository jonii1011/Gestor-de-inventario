package com.example.inventario.Service;

import com.example.inventario.Dto.ProveedorDTO;
import com.example.inventario.Entity.Proveedor;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public interface ProveedorService {
    List<ProveedorDTO> listarProveedores();
    Proveedor crearProveedor(Proveedor proveedor);
    ProveedorDTO actualizar(Long id, Proveedor proveedor);
    void eliminarProveedor(@NotBlank long id);
    ProveedorDTO buscarProveedorPorId(@NotBlank Long id);
    ProveedorDTO convertirAProveedorDTO(Proveedor proveedor);
}
