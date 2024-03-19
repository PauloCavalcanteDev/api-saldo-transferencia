package com.paulodev.apisaldotransferencia.adapters.databases.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "CONTAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Conta implements Serializable {

    @Id
    @Column(name = "conta_id", nullable = false)
    private Long contaId;
    @Column(name = "cliente_id")
    private Long clientId;
    @Column(name = "limite_diario")
    private BigDecimal limiteDiario;
    private BigDecimal saldo;
    @Column(name = "ativa")
    private boolean contaAtiva;
}
