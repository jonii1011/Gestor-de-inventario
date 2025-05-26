package com.example.inventario.Service;

import com.example.inventario.Dto.ProductoDTO;
import com.example.inventario.Entity.Producto;
import com.example.inventario.Repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService{
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> listarProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(this::convertirAProductoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, Producto producto) {
        if (productoRepository.existsById(id)) {
            producto.setId(id);
            Producto productoActualizado = productoRepository.save(producto);
            return convertirAProductoDTO(productoActualizado);
        }
        return null;
    }

    @Override
    public void eliminarProducto(long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else{
            new EntityNotFoundException("Producto no encontrado con ID: " + id);
        }
    }

    @Override
    public ProductoDTO buscarProductoPorId(Long id) {
        return productoRepository.findById(id).
                map(this::convertirAProductoDTO).
                orElse(null);
    }

    @Override
    public ProductoDTO convertirAProductoDTO(Producto producto) {
        return modelMapper.map(producto, ProductoDTO.class);
    }
}
