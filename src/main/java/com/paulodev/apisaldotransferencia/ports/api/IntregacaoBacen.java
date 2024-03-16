package com.paulodev.apisaldotransferencia.ports.api;

import com.paulodev.apisaldotransferencia.dto.notificaBacen.NotificabacenDto;
import com.paulodev.apisaldotransferencia.dto.notificaBacen.RetonoBacenDto;

public interface IntregacaoBacen {
    RetonoBacenDto notificaBacen(NotificabacenDto notificabacenDto);
}
