package com.paulodev.apisaldotransferencia.dto.notificaBacen;

import com.paulodev.apisaldotransferencia.enums.StatusTransacao;
import com.paulodev.apisaldotransferencia.enums.TipoTransacao;

import java.util.Date;

public record NotificabacenDto(
        Long idTransacao,
        Long contaOrigem,
        Long contaDestino,
        Long clientId,
        Enum<TipoTransacao> tipo,
        Date timestamp,
        Enum<StatusTransacao> status

) {
}
