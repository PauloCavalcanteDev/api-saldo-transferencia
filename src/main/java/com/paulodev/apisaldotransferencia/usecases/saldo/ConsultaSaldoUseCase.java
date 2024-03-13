package com.paulodev.apisaldotransferencia.usecases.saldo;

import com.paulodev.apisaldotransferencia.dto.SaldoDto;

import java.math.BigDecimal;

public interface ConsultaSaldoUseCase {

    SaldoDto getSaldo(Long idCliente, BigDecimal saldo);
}
