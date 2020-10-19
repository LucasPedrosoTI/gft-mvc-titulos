package com.gft.estudosmvc.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Titulo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // O DB é responsável por gerar o ID
  private Long id;

  private String descricao;

  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @Temporal(TemporalType.DATE)
  private Date dataVencimento;

  @NumberFormat(pattern = "#,##0.00")
  private BigDecimal valor;

  @Enumerated(EnumType.STRING) // Para salvar no DB de acordo com a string escolhida e não o padrão "ordinal"
                               // (0,1,2,etc)
  private StatusTitulo status;

  public Titulo() {
  }

  public Titulo(Long id, String descricao, Date dataVencimento, BigDecimal valor, StatusTitulo status) {
    this.id = id;
    this.descricao = descricao;
    this.dataVencimento = dataVencimento;
    this.valor = valor;
    this.status = status;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescricao() {
    return this.descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Date getDataVencimento() {
    return this.dataVencimento;
  }

  public void setDataVencimento(Date dataVencimento) {
    this.dataVencimento = dataVencimento;
  }

  public BigDecimal getValor() {
    return this.valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }

  public StatusTitulo getStatus() {
    return this.status;
  }

  public void setStatus(StatusTitulo status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Titulo)) {
      return false;
    }
    Titulo titulo = (Titulo) o;
    return Objects.equals(id, titulo.id) && Objects.equals(descricao, titulo.descricao)
        && Objects.equals(dataVencimento, titulo.dataVencimento) && Objects.equals(valor, titulo.valor)
        && Objects.equals(status, titulo.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, descricao, dataVencimento, valor, status);
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", descricao='" + getDescricao() + "'" + ", dataVencimento='"
        + getDataVencimento() + "'" + ", valor='" + getValor() + "'" + ", status='" + getStatus() + "'" + "}";
  }

}
