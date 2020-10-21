package com.gft.estudosmvc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum StatusTitulo {

  PENDENTE("Pendente"), RECEBIDO("Recebido");

  @Getter
  private String descricao;

}
