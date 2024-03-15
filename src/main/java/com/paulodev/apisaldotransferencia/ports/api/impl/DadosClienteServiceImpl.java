package com.paulodev.apisaldotransferencia.ports.api.impl;

import com.paulodev.apisaldotransferencia.adapters.webservice.buscarCliente.Client.ConsultaClientes;
import com.paulodev.apisaldotransferencia.dto.ClienteDto;
import com.paulodev.apisaldotransferencia.ports.api.DadosClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DadosClienteServiceImpl implements DadosClienteService {

    private final ConsultaClientes consultaClientes;

    @Autowired
    public DadosClienteServiceImpl(ConsultaClientes consultaClientes) {
        this.consultaClientes = consultaClientes;
    }

    @Override
    public ClienteDto buscaDadosCliente(Long clienteId) {
        return consultaClientes.getClientes(clienteId).getBody();

    }
}
