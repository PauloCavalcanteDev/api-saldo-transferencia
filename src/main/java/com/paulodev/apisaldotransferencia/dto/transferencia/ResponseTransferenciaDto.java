package com.paulodev.apisaldotransferencia.dto.transferencia;

import com.paulodev.apisaldotransferencia.enums.StatusEnum;

import java.time.LocalDateTime;
import java.util.Date;

public record ResponseTransferenciaDto(LocalDateTime dataHora, StatusEnum status, String mensagem) {
}
