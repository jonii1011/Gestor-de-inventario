package com.example.inventario.Controller;

import com.example.inventario.Entity.Administrador;
import com.example.inventario.Service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Administrador administrador) {
        Administrador savedAdmin = administradorService.registerAdministrador(administrador);
        return ResponseEntity.ok(savedAdmin);
    }
}
