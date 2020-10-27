package com.gft.estudosmvc.repository;

import java.util.Optional;

import com.gft.estudosmvc.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Usuarios extends JpaRepository<Usuario, Long> {

  public Optional<Usuario> findByEmail(String email);

}
