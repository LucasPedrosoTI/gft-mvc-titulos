package com.gft.estudosmvc.repository;

import com.gft.estudosmvc.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Usuarios extends JpaRepository<Usuario, Long> {

  public Usuario findByEmail(String email);

}
