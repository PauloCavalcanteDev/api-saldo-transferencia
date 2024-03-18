package com.paulodev.apisaldotransferencia.ports.transferencia;

import com.paulodev.apisaldotransferencia.adapters.databases.entities.Transferencia;
import com.paulodev.apisaldotransferencia.exception.LimiteDiarioException;

import java.math.BigDecimal;

public interface TransferenciaService {
    BigDecimal verificaGastoDiario(Long contaId);

    void saveTransferencia(Transferencia transferencia);

    void validaLimiteDiario(Long contaId, BigDecimal limiteDiario, BigDecimal valorTransferencia) throws LimiteDiarioException;


}
