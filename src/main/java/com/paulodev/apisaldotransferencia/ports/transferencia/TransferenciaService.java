package com.paulodev.apisaldotransferencia.ports.transferencia;

import java.math.BigDecimal;

public interface TransferenciaService {
    BigDecimal verificaGastoDiario(Long contaId);
}
