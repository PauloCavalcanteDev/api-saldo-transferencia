package com.paulodev.apisaldotransferencia.dto.transferencia;

import java.math.BigDecimal;
import java.util.Date;

public record TransferenciarealizadaDto(
        Long contaOrigem,
        Long contaDestino,
        BigDecimal valor,

        Date dataHora
) {
}
