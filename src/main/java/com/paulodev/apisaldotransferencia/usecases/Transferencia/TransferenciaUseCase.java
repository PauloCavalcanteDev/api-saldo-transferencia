package com.paulodev.apisaldotransferencia.usecases.Transferencia;

import com.paulodev.apisaldotransferencia.dto.notificaBacen.RetonoBacenDto;
import com.paulodev.apisaldotransferencia.dto.transferencia.SolicitaTransferenciaDto;

public interface TransferenciaUseCase {
    void realizarTransferencia(SolicitaTransferenciaDto transferenciaDto);
    RetonoBacenDto notificarBacen();
}
