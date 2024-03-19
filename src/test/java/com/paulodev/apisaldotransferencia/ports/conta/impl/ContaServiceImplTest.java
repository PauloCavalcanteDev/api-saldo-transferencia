package com.paulodev.apisaldotransferencia.ports.conta.impl;

import com.paulodev.apisaldotransferencia.adapters.databases.entities.Conta;
import com.paulodev.apisaldotransferencia.adapters.databases.repository.ContaRepository;
import com.paulodev.apisaldotransferencia.conveters.ContaConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
class ContaServiceImplTest {

    @Mock
    ContaRepository repository;

    @Mock
    ContaConverter converter;


    private ContaServiceImpl contaServiceImpl;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        this.contaServiceImpl = new ContaServiceImpl(repository, converter);
    }

    @Test
    void deveRetornarAcontaAoReceberoClientID() {
        Conta conta = new Conta(
                10L,
                100L,
                new BigDecimal(1000),
                new BigDecimal(2000),
                true);

        Mockito.when(repository.findByIdContaAndClientId(10L, 100L)).thenReturn(Optional.of(conta));

        var result = contaServiceImpl.consultaConta(10L, 100L);
        var expect = conta.getContaId();
        assertEquals(result.get().getContaId(), expect);

    }

    @Test
    void consultaSaldoCliente() {
    }

    @Test
    void buscaConta() {
    }

    @Test
    void retirarSaldo() {
    }

    @Test
    void depositar() {
    }

    @Test
    void consultaLimiteDiario() {
    }
}