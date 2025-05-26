package com.example.inventario.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Column(nullable = false)
    private String apellido;

    private String telefono;

    @Email(message = "Email inválido")
    private String email;

    @Column(length = 500)
    private String direccion;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Venta> ventas;
}
