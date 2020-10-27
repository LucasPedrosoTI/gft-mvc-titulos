package com.gft.estudosmvc.repository;

import java.util.List;

import com.gft.estudosmvc.model.Titulo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Titulos extends JpaRepository<Titulo, Long> {

  public List<Titulo> findByUsuarioIdAndDescricaoContaining(Long usuarioId, String descricao);
}
