package com.example.inventario.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;


import java.time.LocalDate;

@Entity
@Table(name = "productos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "La marca es obligatoria")
    private String marca;
    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;
    @NotBlank(message = "El almacenamiento es obligatorio")
    private String almacenamiento;
    @NotBlank(message = "El color es obligatorio")
    private String color;
    @NotBlank(message = "El imei es obligatorio")
    private String imei;
    @NotNull
    private Double precioCompra;
    @NotNull
    private Double precioVenta;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoProducto estado;
    private LocalDate fechaIngreso;
    private String descripcion;
}
