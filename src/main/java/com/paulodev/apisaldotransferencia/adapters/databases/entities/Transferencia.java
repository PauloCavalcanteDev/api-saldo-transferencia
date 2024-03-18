package com.paulodev.apisaldotransferencia.adapters.databases.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "TRANSFERENCIAS")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Transferencia {
    @Id
    @Column(name = "transf_id", nullable = false)
    private Long transferenciaId;

    @Column(name = "conta_id", nullable = false)
    private Long contaOrigem;

    @Column(name = "conta_dstn", nullable = false)
    private Long contaDestino;

    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "status_bacen", nullable = false)
    private String status;

    @Column(name = "data_hora")
    private LocalDateTime timestamp;


}
