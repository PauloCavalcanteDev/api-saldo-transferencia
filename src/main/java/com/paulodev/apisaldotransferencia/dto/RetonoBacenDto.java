package com.paulodev.apisaldotransferencia.dto;


import java.util.Date;

public record RetonoBacenDto(Long idTransacao, String msgRetorno, Date data) {
}
