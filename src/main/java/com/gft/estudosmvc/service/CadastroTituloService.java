package com.gft.estudosmvc.service;

import java.util.List;

import com.gft.estudosmvc.model.StatusTitulo;
import com.gft.estudosmvc.model.Titulo;
import com.gft.estudosmvc.model.Usuario;
import com.gft.estudosmvc.repository.TituloFilter;
import com.gft.estudosmvc.repository.Titulos;
import com.gft.estudosmvc.repository.Usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CadastroTituloService {
  @Autowired
  private Titulos titulos;

  @Autowired
  Usuarios usuarios;

  public void salvar(Long usuarioId, Titulo titulo) {
    try {
      Usuario usuario = usuarios.getOne(usuarioId);

      titulo.setUsuario(usuario);

      titulos.save(titulo);

    } catch (DataIntegrityViolationException e) {
      throw new IllegalArgumentException("Formato de data inválido");
    }
  }

  public void deletar(Long id) {
    titulos.deleteById(id);
  }

  public String receber(Long id) {
    Titulo titulo = titulos.findById(id).get();
    titulo.setStatus(StatusTitulo.RECEBIDO);
    titulos.save(titulo);

    return StatusTitulo.RECEBIDO.getDescricao();
  }

  public List<Titulo> filtrar(Long usuarioId, TituloFilter filtro) {
    final String descricao = filtro.getDescricao() == null ? "" : filtro.getDescricao();
    return titulos.findByUsuarioIdAndDescricaoContaining(usuarioId, descricao);
  }

}
