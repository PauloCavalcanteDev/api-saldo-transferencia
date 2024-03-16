package com.paulodev.apisaldotransferencia.adapters.validador;

import com.paulodev.apisaldotransferencia.exception.ContaInativaException;
import com.paulodev.apisaldotransferencia.exception.LimiteDiarioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class ValidadorConta {

    public void contaAtiva(boolean contaAtiva) throws ContaInativaException {
        if (!contaAtiva) {
            throw new ContaInativaException("CONTA INATIVA");
        }
    }

    public void validaLimiteDiario(BigDecimal limite, BigDecimal valorTransferido) throws LimiteDiarioException {
        if (limite.compareTo(valorTransferido) < 0) {
            throw new LimiteDiarioException("LIMITE DIARIO EXCEDIDO");
        }
        //TODO incluir metódo para bsucar limite diario e salvar em cache

    }
}
