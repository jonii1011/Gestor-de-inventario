package com.example.inventario.Repository;

import com.example.inventario.Entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Administrador findByEmail(String email);
}
