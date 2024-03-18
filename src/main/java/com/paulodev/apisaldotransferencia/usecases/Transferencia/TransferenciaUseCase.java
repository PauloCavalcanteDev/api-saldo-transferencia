package com.paulodev.apisaldotransferencia.usecases.Transferencia;

import com.paulodev.apisaldotransferencia.dto.transferencia.ResponseTransferenciaDto;
import com.paulodev.apisaldotransferencia.dto.transferencia.SolicitaTransferenciaDto;
import com.paulodev.apisaldotransferencia.exception.ErroTransferenciaException;

public interface TransferenciaUseCase {
    ResponseTransferenciaDto realizarTransferencia(SolicitaTransferenciaDto transferenciaDto) throws ErroTransferenciaException;

}
