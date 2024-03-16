package com.paulodev.apisaldotransferencia.dto.notificaBacen;

import com.paulodev.apisaldotransferencia.enums.StatusTransacao;
import com.paulodev.apisaldotransferencia.enums.TipoTransacao;

import java.time.LocalDateTime;

public record NotificabacenDto(
        Long idTransacao,
        Long contaOrigem,
        Long contaDestino,
        Long clientId,
        TipoTransacao tipo,
        LocalDateTime timestamp,
        StatusTransacao status

) {
}
