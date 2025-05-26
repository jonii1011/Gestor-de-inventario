package com.example.inventario.Dto;

import com.example.inventario.Entity.EstadoProducto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductoDTO {
    private String marca;
    private String modelo;
    private String almacenamiento;
    private String color;
    private String imei;
    private Double precioCompra;
    private Double precioVenta;
    @Enumerated(EnumType.STRING)
    private EstadoProducto estado;
    private LocalDate fechaIngreso;
    private String descripcion;
}
