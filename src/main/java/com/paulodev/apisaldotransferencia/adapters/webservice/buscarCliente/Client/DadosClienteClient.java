package com.paulodev.apisaldotransferencia.adapters.webservice.buscarCliente.Client;

import com.paulodev.apisaldotransferencia.dto.ClienteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "buscarDadosCliente", url = "http://localhost:8090")
public interface DadosClienteClient {
    @GetMapping("/clientes/{id}")
    public ResponseEntity<ClienteDto> getClientes(@PathVariable("id")  Long id);

    @PostMapping(value = "/clientes", consumes = MediaType.APPLICATION_JSON_VALUE)
    void cadastra(@RequestBody ClienteDto dto);


}
