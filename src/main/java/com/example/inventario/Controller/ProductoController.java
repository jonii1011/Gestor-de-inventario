package com.example.inventario.Controller;

import com.example.inventario.Dto.ProductoDTO;
import com.example.inventario.Entity.Producto;
import com.example.inventario.Service.ProductoService;
import jakarta.persistence.EntityNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Operation(summary = "Listar todos los productos", description = "Devuelve una lista de todos los productos disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos recuperada con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProductos() {
        List<ProductoDTO> productos = productoService.listarProductos();
        return ResponseEntity.ok(productos);
    }

    @Operation(summary = "Crear un nuevo producto", description = "Crea un nuevo producto y lo almacena en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.crearProducto(producto);
        ProductoDTO productoDTO = productoService.convertirAProductoDTO(nuevoProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoDTO);
    }

    @Operation(summary = "Actualizar un producto existente", description = "Actualiza un producto por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@Parameter(description = "ID del producto a actualizar") @PathVariable Long id, @RequestBody Producto producto) {
        ProductoDTO productoActualizado = productoService.actualizarProducto(id, producto);
        if (productoActualizado != null) {
            return ResponseEntity.ok(productoActualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Eliminar un producto", description = "Elimina un producto por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@Parameter(description = "ID del producto a eliminar") @PathVariable Long id) {
        try {
            productoService.eliminarProducto(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Buscar un producto por ID", description = "Devuelve un producto específico por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> buscarProductoPorId(@Parameter(description = "ID del producto a buscar") @PathVariable Long id) {
        ProductoDTO productoDTO = productoService.buscarProductoPorId(id);
        if (productoDTO != null) {
            return ResponseEntity.ok(productoDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

