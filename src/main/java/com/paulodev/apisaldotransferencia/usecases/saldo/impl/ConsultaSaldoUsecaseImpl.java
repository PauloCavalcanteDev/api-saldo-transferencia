package com.paulodev.apisaldotransferencia.usecases.saldo.impl;

import com.paulodev.apisaldotransferencia.adapters.databases.entities.Conta;
import com.paulodev.apisaldotransferencia.adapters.databases.repository.ContaRepository;
import com.paulodev.apisaldotransferencia.adapters.webservice.buscarCliente.Client.DadosClienteClient;
import com.paulodev.apisaldotransferencia.dto.SaldoDto;
import com.paulodev.apisaldotransferencia.usecases.saldo.ConsultaSaldoUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class ConsultaSaldoUsecaseImpl implements ConsultaSaldoUseCase {

    private final ContaRepository contaRepository;
    private final DadosClienteClient dadosClienteClient;

    @Autowired
    public ConsultaSaldoUsecaseImpl(ContaRepository contaRepository, DadosClienteClient dadosClienteClient) {
        this.contaRepository = contaRepository;
        this.dadosClienteClient = dadosClienteClient;
    }

    @Override
    public SaldoDto getSaldo(Long clienteId, Long contaId) {

        var contaRetorno = contaRepository.findByIdContaAndClientId(contaId, clienteId);
        var cliente = dadosClienteClient.getClientes(clienteId);
        var saldo = this.toDto(contaRetorno.get(), cliente.getBody().nome());
        log.info("Cliente recuperado -> {}", cliente.getBody().nome());
        return saldo;
    }

    private SaldoDto toDto(Conta contaRetorno, String name) {
        return SaldoDto.builder()
                .cliente(name)
                .conta(contaRetorno.getContaId())
                .limiteDiario(contaRetorno.getLimiteDiario())
                .saldo(contaRetorno.getSaldo())
                .build();
    }
}
