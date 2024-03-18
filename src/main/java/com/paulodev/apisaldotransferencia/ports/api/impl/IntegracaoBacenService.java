package com.paulodev.apisaldotransferencia.ports.api.impl;

import com.paulodev.apisaldotransferencia.adapters.webservice.bacen.client.BacenClient;
import com.paulodev.apisaldotransferencia.dto.notificaBacen.NotificabacenDto;
import com.paulodev.apisaldotransferencia.dto.notificaBacen.RetonoBacenDto;
import com.paulodev.apisaldotransferencia.ports.api.IntregacaoBacen;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IntegracaoBacenService implements IntregacaoBacen {

    private final BacenClient bacenClient;

    public IntegracaoBacenService(BacenClient bacenClient) {
        this.bacenClient = bacenClient;
    }

    @Override
    public RetonoBacenDto notificaBacen(NotificabacenDto notificabacenDto) {
        log.info("NOTIFICANDO BACEN TRANSFERENCIA REALIZADA");
        return this.bacenClient.enviarBacen(notificabacenDto).getBody();
    }
}
