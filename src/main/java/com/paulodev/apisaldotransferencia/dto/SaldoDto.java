package com.paulodev.apisaldotransferencia.dto;

import java.math.BigDecimal;

public record SaldoDto(String nomeCliente, Long conta, BigDecimal saldo, BigDecimal limiteDiario) {
}
