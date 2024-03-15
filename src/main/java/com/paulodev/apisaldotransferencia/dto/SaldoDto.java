package com.paulodev.apisaldotransferencia.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SaldoDto {
    private String nomeCliente;
    private Long conta;
    private BigDecimal saldo;
    private BigDecimal limiteDiario;
}
