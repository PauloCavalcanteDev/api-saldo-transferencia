package com.paulodev.apisaldotransferencia.conveters;

import com.paulodev.apisaldotransferencia.adapters.databases.entities.Transferencia;
import com.paulodev.apisaldotransferencia.dto.transferencia.SolicitaTransferenciaDto;
import com.paulodev.apisaldotransferencia.enums.StatusEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class TransferenciaConverter {

    public Transferencia dtoToTransferencia(SolicitaTransferenciaDto dto) {
        var transferencia = new Transferencia();
        transferencia.setTransferenciaId(new Random().nextLong(100, 100000000));
        transferencia.setContaOrigem(dto.contaOrigem());
        transferencia.setContaDestino(dto.contaDestino());
        transferencia.setValor(dto.valor());
        transferencia.setStatus(StatusEnum.REALIZADA.toString());
        transferencia.setTimestamp(LocalDateTime.now());

        return transferencia;


    }
}
