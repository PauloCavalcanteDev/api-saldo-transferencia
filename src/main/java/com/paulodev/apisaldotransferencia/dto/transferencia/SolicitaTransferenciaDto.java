package com.paulodev.apisaldotransferencia.dto.transferencia;

import java.math.BigDecimal;

public record SolicitaTransferenciaDto(
        Long clienteSolicitante,
        Long contaOrigem,
        Long contaDestino,
        BigDecimal valor
) {

}
