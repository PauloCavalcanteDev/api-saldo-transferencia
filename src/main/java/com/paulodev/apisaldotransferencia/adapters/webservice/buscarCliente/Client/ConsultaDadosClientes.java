package com.paulodev.apisaldotransferencia.adapters.webservice.buscarCliente.Client;

import com.paulodev.apisaldotransferencia.adapters.webservice.buscarCliente.config.DadosClienteConfig;
import com.paulodev.apisaldotransferencia.dto.ClienteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "buscarDadosCliente",
        url = "http://localhost:8090",
        configuration = DadosClienteConfig.class)
public interface ConsultaDadosClientes {

    @GetMapping("/cliente/{id}")
    public ResponseEntity<ClienteDto> getClientes(@PathVariable("id") Long id);


}
