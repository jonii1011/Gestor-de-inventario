package com.example.inventario.Dto;

import com.example.inventario.Entity.Usuario;
import com.example.inventario.Entity.MetodoPago;
import com.example.inventario.Entity.Producto;
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
public class VentaDTO {
    private Long usuarioId;
    private ProductoDTO producto;
    private LocalDate fechaVenta;
    private Double precioFinal;
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;
}
