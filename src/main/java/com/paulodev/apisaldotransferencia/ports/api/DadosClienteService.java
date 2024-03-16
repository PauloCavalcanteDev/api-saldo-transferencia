package com.paulodev.apisaldotransferencia.ports.api;

import com.paulodev.apisaldotransferencia.dto.ClienteDto;
import com.paulodev.apisaldotransferencia.exception.ErroBuscarClienteException;

public interface DadosClienteService {

    ClienteDto buscaDadosCliente(Long clienteId) throws ErroBuscarClienteException;
}
