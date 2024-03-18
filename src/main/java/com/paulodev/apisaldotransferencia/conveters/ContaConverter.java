package com.paulodev.apisaldotransferencia.conveters;

import com.paulodev.apisaldotransferencia.adapters.databases.entities.Conta;
import com.paulodev.apisaldotransferencia.dto.SaldoDto;
import org.springframework.stereotype.Component;

@Component
public class ContaConverter {

    public SaldoDto toDto(Conta contaRetorno, String name) {
        return SaldoDto.builder()
                .nomeCliente(name)
                .conta(contaRetorno.getContaId())
                .saldo(contaRetorno.getSaldo())
                .build();
    }
}
