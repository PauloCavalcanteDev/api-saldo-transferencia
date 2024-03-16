package com.paulodev.apisaldotransferencia.adapters.webservice.bacen.client;


import com.paulodev.apisaldotransferencia.dto.notificaBacen.NotificabacenDto;
import com.paulodev.apisaldotransferencia.dto.notificaBacen.RetonoBacenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "notificadorBacen", url = "http://localhost:8090")
public interface BacenClient {

    @PostMapping(value = "/bacen", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RetonoBacenDto> enviarBacen(@RequestBody NotificabacenDto dto);


}
