package com.example.inventario.Service;

import com.example.inventario.Dto.ProductoDTO;
import com.example.inventario.Entity.Producto;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public interface ProductoService {
    List<ProductoDTO> listarProductos();
    Producto crearProducto(Producto producto);
    ProductoDTO actualizarProducto(Long id, Producto producto);
    void eliminarProducto(@NotBlank long id);
    ProductoDTO buscarProductoPorId(@NotBlank Long id);
    ProductoDTO convertirAProductoDTO(Producto producto);
}