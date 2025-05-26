package com.example.inventario.Dto;

import com.example.inventario.Entity.TipoProveedor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProveedorDTO {
    private String nombre;
    private String telefono;
    @Enumerated(EnumType.STRING)
    private TipoProveedor tipoProveedor;
}
