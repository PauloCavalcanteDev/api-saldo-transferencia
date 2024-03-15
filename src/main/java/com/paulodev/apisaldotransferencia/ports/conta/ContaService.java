package com.paulodev.apisaldotransferencia.ports.conta;

import com.paulodev.apisaldotransferencia.adapters.databases.entities.Conta;
import com.paulodev.apisaldotransferencia.dto.SaldoDto;

import java.util.Optional;

public interface ContaService {

    Optional<Conta> consultaConta(Long contaId, Long clientId);

    SaldoDto consultaSaldo(Conta conta, String nome);


}
