package com.example.inventario.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "proveedores")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El telefono es obligatorio")
    private String telefono;

    @Enumerated(EnumType.STRING)
    private TipoProveedor tipoProveedor;
}
