package com.paulodev.apisaldotransferencia.dto.notificaBacen;


import java.util.Date;

public record RetonoBacenDto(Long idTransacao, String msgRetorno, Date data) {
}
