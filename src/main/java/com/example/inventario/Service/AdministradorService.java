package com.example.inventario.Service;

import com.example.inventario.Entity.Administrador;
import com.example.inventario.Repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdministradorService implements UserDetailsService {

    private final AdministradorRepository administradorRepository;
    private final PasswordEncoder passwordEncoder; // Cambiar a variable final

    @Autowired
    public AdministradorService(AdministradorRepository administradorRepository, PasswordEncoder passwordEncoder) {
        this.administradorRepository = administradorRepository;
        this.passwordEncoder = passwordEncoder; // Inyección a través del constructor
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Administrador administrador = administradorRepository.findByEmail(email);
        if (administrador == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new org.springframework.security.core.userdetails.User(administrador.getEmail(), administrador.getPassword(), new ArrayList<>());
    }

    public Administrador registerAdministrador(Administrador administrador) {
        String encodedPassword = passwordEncoder.encode(administrador.getPassword());
        administrador.setPassword(encodedPassword);
        return administradorRepository.save(administrador);
    }
}

