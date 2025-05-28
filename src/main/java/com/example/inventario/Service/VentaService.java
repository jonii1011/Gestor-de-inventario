package com.example.inventario.Service;

import com.example.inventario.Dto.VentaDTO;
import com.example.inventario.Entity.Venta;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public interface VentaService {
    List<VentaDTO> listarVenta();
    Venta crearVenta(Venta venta);
    VentaDTO actualizarVenta(Long id, Venta venta);
    void eliminarVenta(@NotBlank long id);
    VentaDTO buscarProductoPorId(@NotBlank Long id);
    VentaDTO convertirAVentaDTO(Venta venta);
}

