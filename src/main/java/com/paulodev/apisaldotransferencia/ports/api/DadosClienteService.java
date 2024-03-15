package com.paulodev.apisaldotransferencia.ports.api;

import com.paulodev.apisaldotransferencia.dto.ClienteDto;

public interface DadosClienteService {

    ClienteDto buscaDadosCliente(Long clienteId);
}
