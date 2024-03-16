package com.paulodev.apisaldotransferencia.usecases.saldo.impl;

import com.paulodev.apisaldotransferencia.dto.SaldoDto;
import com.paulodev.apisaldotransferencia.exception.DadosInseridoInvalidos;
import com.paulodev.apisaldotransferencia.ports.api.DadosClienteService;
import com.paulodev.apisaldotransferencia.ports.conta.ContaService;
import com.paulodev.apisaldotransferencia.usecases.saldo.ConsultaSaldoUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.text.MessageFormat.format;

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
    public SaldoDto getSaldo(Long clienteId, Long contaId) throws DadosInseridoInvalidos {
        try {
            var conta = contaService.consultaConta(contaId, clienteId).get();
            var cliente = dadosClienteService.buscaDadosCliente(clienteId).nome();

            return contaService.consultaSaldoCliente(conta, cliente);

        } catch (Exception ex) {
            throw new DadosInseridoInvalidos(format("CONTA ou CLIENTE INVALIDO -. {} ", ex.getMessage()));
        }

    }

}
