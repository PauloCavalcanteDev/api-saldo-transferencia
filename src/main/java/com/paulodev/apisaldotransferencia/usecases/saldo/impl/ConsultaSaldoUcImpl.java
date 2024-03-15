package com.paulodev.apisaldotransferencia.usecases.saldo.impl;

import com.paulodev.apisaldotransferencia.dto.SaldoDto;
import com.paulodev.apisaldotransferencia.ports.api.DadosClienteService;
import com.paulodev.apisaldotransferencia.ports.conta.ContaService;
import com.paulodev.apisaldotransferencia.usecases.saldo.ConsultaSaldoUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsultaSaldoUcImpl implements ConsultaSaldoUseCase {


    private final DadosClienteService dadosClienteService;
    private final ContaService contaService;


    @Autowired
    public ConsultaSaldoUcImpl(DadosClienteService dadosClienteService, ContaService contaService) {
        this.dadosClienteService = dadosClienteService;
        this.contaService = contaService;
    }

    @Override
    public SaldoDto getSaldo(Long clienteId, Long contaId) {

        return contaService.consultaSaldoCliente(
                contaService.consultaConta(contaId, clienteId).get(),
                dadosClienteService.buscaDadosCliente(clienteId).nome()
        );

    }


}
