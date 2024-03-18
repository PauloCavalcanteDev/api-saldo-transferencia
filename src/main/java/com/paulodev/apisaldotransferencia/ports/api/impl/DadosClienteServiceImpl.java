package com.paulodev.apisaldotransferencia.ports.api.impl;

import com.paulodev.apisaldotransferencia.adapters.webservice.buscarCliente.Client.ConsultaDadosClientes;
import com.paulodev.apisaldotransferencia.dto.ClienteDto;
import com.paulodev.apisaldotransferencia.exception.ErroTransferenciaException;
import com.paulodev.apisaldotransferencia.ports.api.DadosClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@Slf4j
public class DadosClienteServiceImpl implements DadosClienteService {

    private final ConsultaDadosClientes consultaDadosClientes;

    @Autowired
    public DadosClienteServiceImpl(ConsultaDadosClientes consultaDadosClientes) {
        this.consultaDadosClientes = consultaDadosClientes;
    }

    @Override
//    @Cacheable("cliente")
    public ClienteDto buscaDadosCliente(Long clienteId) throws ErroTransferenciaException {
        log.info("Identificando cliente: {}", clienteId);
        try {
            var cliente = consultaDadosClientes.getClientes(clienteId).getBody();
            log.info("Cliente Identificado: {} - ID -> {} ", cliente.nome(), clienteId);
            return cliente;
        } catch (Exception ex) {
            log.error("FALHA AO IDENTIFICAR CLIENTE: {}", ex.getMessage());
            throw new ErroTransferenciaException(format("CLIENTE NAO ENCONTRADO {}", ex.getMessage()));
        }


    }
}
