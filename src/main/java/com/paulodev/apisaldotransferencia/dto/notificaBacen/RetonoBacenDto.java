package com.paulodev.apisaldotransferencia.dto.notificaBacen;


import com.paulodev.apisaldotransferencia.enums.TipoTransacao;

public record RetonoBacenDto(
        Long numeroTransacao,
        TipoTransacao tipoTransacao,
        Long idCliente) {
}
