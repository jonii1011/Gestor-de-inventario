package com.example.inventario.Service;

import com.example.inventario.Dto.VentaDTO;
import com.example.inventario.Entity.Venta;
import com.example.inventario.Repository.VentaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements VentaService{
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<VentaDTO> listarVenta() {
        List<Venta> ventas = ventaRepository.findAll();
        return ventas.stream()
                .map(this::convertirAVentaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Venta crearVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public VentaDTO actualizar(Long id, Venta venta) {
        if (ventaRepository.existsById(id)) {
            venta.setId(id);
            Venta ventaActualizado = ventaRepository.save(venta);
            return convertirAVentaDTO(ventaActualizado);
        }
        return null;
    }

    @Override
    public void eliminarVenta(long id) {
        if (ventaRepository.existsById(id)) {
            ventaRepository.deleteById(id);
        } else{
            new EntityNotFoundException("Venta no encontrado con ID: " + id);
        }
    }

    @Override
    public VentaDTO buscarProductoPorId(Long id) {
        return ventaRepository.findById(id).
                map(this::convertirAVentaDTO).
                orElse(null);
    }

    @Override
    public VentaDTO convertirAVentaDTO(Venta venta) {
        return modelMapper.map(venta, VentaDTO.class);
    }
}
