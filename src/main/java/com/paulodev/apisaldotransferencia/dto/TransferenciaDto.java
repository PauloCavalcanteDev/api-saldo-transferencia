package com.paulodev.apisaldotransferencia.dto;

import java.math.BigDecimal;

public record TransferenciaDto(

        Long contaOrigem,
        Long contaDestino,
        BigDecimal valor
) {

}
