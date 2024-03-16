package com.paulodev.apisaldotransferencia.adapters.validador;

import com.paulodev.apisaldotransferencia.exception.ContaInativaException;
import com.paulodev.apisaldotransferencia.exception.LimiteDiarioException;
import com.paulodev.apisaldotransferencia.ports.conta.ContaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class ValidadorConta {

    private final ContaService contaService;

    public ValidadorConta(ContaService contaService) {
        this.contaService = contaService;
    }


    public void contaAtiva(boolean contaAtiva) throws ContaInativaException {
        if (!contaAtiva) {
            throw new ContaInativaException("CONTA INATIVA");
        }
    }

    public void validaLimiteDiario(BigDecimal limite, BigDecimal valor) throws LimiteDiarioException {

        if (limite.compareTo(valor) < 0) {
            throw new LimiteDiarioException("LIMITE DIARIO EXCEDIDO");
        }


    }
}
