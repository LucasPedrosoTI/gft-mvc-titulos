package com.gft.estudosmvc.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Titulo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // O DB é responsável por gerar o ID
  private Long id;

  @NotBlank(message = "Descrição é obrigatória")
  @Size(max = 60, message = "A descrição não pode conter mais de 60 caracteres")

  private String descricao;

  @NotNull(message = "Data de vencimento é obrigatória")
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @Temporal(TemporalType.DATE)
  private Date dataVencimento;

  @NotNull(message = "Valor é obrigatório")
  @DecimalMin(value = "0.01", message = "Valor não pode ser menor que 0,01")
  @DecimalMax(value = "9999999999", message = "Valor não pode ser maior que 9999999999")
  @NumberFormat(pattern = "#,##0.00")
  private BigDecimal valor;

  @Enumerated(EnumType.STRING) // Para salvar no DB de acordo com a string escolhida e não o padrão "ordinal"
                               // (0,1,2,etc)
  private StatusTitulo status;

  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  public boolean isPendente() {
    return StatusTitulo.PENDENTE.equals(this.status);
  }

}
