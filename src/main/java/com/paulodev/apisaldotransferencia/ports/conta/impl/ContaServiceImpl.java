package com.paulodev.apisaldotransferencia.ports.conta.impl;

import com.paulodev.apisaldotransferencia.adapters.databases.entities.Conta;
import com.paulodev.apisaldotransferencia.adapters.databases.repository.ContaRepository;
import com.paulodev.apisaldotransferencia.conveters.ContaConverter;
import com.paulodev.apisaldotransferencia.dto.SaldoDto;
import com.paulodev.apisaldotransferencia.ports.conta.ContaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
public class ContaServiceImpl implements ContaService {

    private final ContaRepository repository;
    private final ContaConverter converter;

    @Autowired
    public ContaServiceImpl(ContaRepository repository, ContaConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public Optional<Conta> consultaConta(Long contaId, Long clientId) {
        return repository.findByIdContaAndClientId(contaId, clientId);

    }

    @Override
    public SaldoDto consultaSaldoCliente(Conta conta, String nome) {

        return converter.toDto(conta, nome);
    }

    @Override
    public Conta buscaConta(Long contaId) {

        return repository.findById(contaId).get();
    }

    @Override
    public void retirarSaldo(BigDecimal valor, Long contaId) {
        repository.trasnferir(valor, contaId);
    }

    @Override
    public void depositar(BigDecimal valorDeposito, Long idContaDestino) {
        repository.depositar(valorDeposito, idContaDestino);
    }

    @Override
    public BigDecimal consultaLimiteDiario(Long contaId, Long clientId) {
        return repository.findLimiteDiario(contaId, clientId);
    }


    private SaldoDto toDto(Conta contaRetorno, String name) {
        return SaldoDto.builder()
                .nomeCliente(name)
                .conta(contaRetorno.getContaId())
                .limiteDiario(contaRetorno.getLimiteDiario())
                .saldo(contaRetorno.getSaldo())
                .build();
    }


}
