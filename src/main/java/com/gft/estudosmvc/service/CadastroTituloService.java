package com.gft.estudosmvc.service;

import com.gft.estudosmvc.model.Titulo;
import com.gft.estudosmvc.repository.Titulos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CadastroTituloService {
  @Autowired
  private Titulos titulos;

  public void save(Titulo titulo) {
    try {
      titulos.save(titulo);

    } catch (DataIntegrityViolationException e) {
      throw new IllegalArgumentException("Formato de data inválido");
    }
  }

  public void delete(Long id) {
    titulos.deleteById(id);
  }

}
