package com.paulodev.apisaldotransferencia.ports.api.impl;

import com.paulodev.apisaldotransferencia.adapters.webservice.buscarCliente.Client.ConsultaDadosClientes;
import com.paulodev.apisaldotransferencia.dto.ClienteDto;
import com.paulodev.apisaldotransferencia.exception.ErroBuscarClienteException;
import com.paulodev.apisaldotransferencia.ports.api.DadosClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable("cliente")
    public ClienteDto buscaDadosCliente(Long clienteId) throws ErroBuscarClienteException {
        try {
            return consultaDadosClientes.getClientes(clienteId).getBody();
        } catch (Exception ex) {
            throw new ErroBuscarClienteException(format("CLIENTE NAO ENCONTRADO {}", ex.getMessage()));
        }


    }
}
