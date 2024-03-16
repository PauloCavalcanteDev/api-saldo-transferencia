package com.paulodev.apisaldotransferencia.usecases.saldo;

import com.paulodev.apisaldotransferencia.dto.SaldoDto;
import com.paulodev.apisaldotransferencia.exception.ContaInvalidaExption;

import java.math.BigDecimal;

public interface ConsultaSaldoUseCase {

    SaldoDto getSaldo(Long clientId, Long contaId) throws ContaInvalidaExption;
}
