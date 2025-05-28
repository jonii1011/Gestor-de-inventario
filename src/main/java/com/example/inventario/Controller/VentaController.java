package com.example.inventario.Controller;

import com.example.inventario.Dto.VentaDTO;
import com.example.inventario.Entity.Venta;
import com.example.inventario.Service.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Operation(summary = "Listar todas las ventas", description = "Devuelve una lista de todas las ventas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ventas recuperada con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<VentaDTO>> listarVentas() {
        List<VentaDTO> ventas = ventaService.listarVenta();
        return ResponseEntity.ok(ventas);
    }

    @Operation(summary = "Crear una nueva venta", description = "Crea una nueva venta y lo almacena en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venta creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping
    public ResponseEntity<VentaDTO> crearVenta(@RequestBody Venta venta) {
        Venta nuevaVenta = ventaService.crearVenta(venta);
        VentaDTO ventaDTO = ventaService.convertirAVentaDTO(nuevaVenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaDTO);
    }

    @Operation(summary = "Actualizar una venta existente", description = "Actualiza una venta por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta actualizada con éxito"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> actualizarVenta(@Parameter(description = "ID de la venta a actualizar") @PathVariable Long id, @RequestBody Venta venta) {
        VentaDTO ventaDTO = ventaService.actualizarVenta(id, venta);
        if (ventaDTO != null) {
            return ResponseEntity.ok(ventaDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@Parameter(description = "ID de la venta a eliminar") @PathVariable Long id) {
        try {
            ventaService.eliminarVenta(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Buscar una venta por ID", description = "Devuelve una venta específico por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta encontrada"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> buscarVentaPorId(@Parameter(description = "ID de la venta a buscar") @PathVariable Long id) {
        VentaDTO ventaDTO = ventaService.buscarProductoPorId(id);
        if (ventaDTO != null) {
            return ResponseEntity.ok(ventaDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
