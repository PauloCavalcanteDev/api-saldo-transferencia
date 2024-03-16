package com.paulodev.apisaldotransferencia.ports.conta;

import com.paulodev.apisaldotransferencia.adapters.databases.entities.Conta;
import com.paulodev.apisaldotransferencia.dto.SaldoDto;

import java.math.BigDecimal;
import java.util.Optional;

public interface ContaService {

    Optional<Conta> consultaConta(Long contaId, Long clientId);

    SaldoDto consultaSaldoCliente(Conta conta, String nome);

    Conta buscaConta(Long contaId);

    void retirarSaldo(BigDecimal valor, Long contaId);

    void depositar(BigDecimal valor, Long contaId);


}
