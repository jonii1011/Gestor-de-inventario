package com.example.inventario.Dto;

import com.example.inventario.Entity.Venta;
import jakarta.persistence.*;
import java.util.List;

public class ClienteDTO {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String direccion;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Venta> ventas;
}
