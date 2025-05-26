package com.example.inventario.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "ventas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull(message = "El cliente es obligatorio")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    @NotNull(message = "El producto es obligatorio")
    private Producto producto;

    @Column(nullable = false)
    private LocalDate fechaVenta;

    @NotNull(message = "El precio final es obligatorio")
    private Double precioFinal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MetodoPago metodoPago;

    public Double calcularGanancia() {
        return precioFinal - producto.getPrecioCompra();
    }
}
