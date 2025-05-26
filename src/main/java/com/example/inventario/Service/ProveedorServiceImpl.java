package com.example.inventario.Service;

import com.example.inventario.Dto.ProveedorDTO;
import com.example.inventario.Entity.Proveedor;
import com.example.inventario.Repository.ProveedorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedorServiceImpl implements ProveedorService{
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<ProveedorDTO> listarProveedores() {
        List<Proveedor> proveedores = proveedorRepository.findAll();
        return proveedores.stream()
                .map(this::convertirAProveedorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Proveedor crearProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public ProveedorDTO actualizar(Long id, Proveedor proveedor) {
        if (proveedorRepository.existsById(id)) {
            proveedor.setId(id);
            Proveedor proveedorActualizado = proveedorRepository.save(proveedor);
            return convertirAProveedorDTO(proveedorActualizado);
        }
        return null;
    }

    @Override
    public void eliminarProveedor(long id) {
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
        } else{
            new EntityNotFoundException("Proveedor no encontrado con ID: " + id);
        }
    }

    @Override
    public ProveedorDTO buscarProveedorPorId(Long id) {
        return proveedorRepository.findById(id).
                map(this::convertirAProveedorDTO).
                orElse(null);
    }

    @Override
    public ProveedorDTO convertirAProveedorDTO(Proveedor proveedor) {
        return modelMapper.map(proveedor, ProveedorDTO.class);
    }
}
