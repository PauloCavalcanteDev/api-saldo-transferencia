package com.paulodev.apisaldotransferencia.ports.transferencia.impl;

import com.paulodev.apisaldotransferencia.adapters.databases.entities.Transferencia;
import com.paulodev.apisaldotransferencia.adapters.databases.repository.TransferenciaRepository;
import com.paulodev.apisaldotransferencia.exception.LimiteDiarioException;
import com.paulodev.apisaldotransferencia.ports.transferencia.TransferenciaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@Slf4j
public class TransferenciaServiceImpl implements TransferenciaService {

    private final TransferenciaRepository repository;

    public TransferenciaServiceImpl(TransferenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    public BigDecimal verificaGastoDiario(Long contaId) {
        return repository.getValorDiario(contaId);
    }

    public void validaLimiteDiario(Long contaId, BigDecimal limiteDiario, BigDecimal valorTransferencia) throws LimiteDiarioException {
        var gastoDiario = this.verificaGastoDiario(contaId);

        if (Objects.nonNull(gastoDiario) && (limiteDiario).compareTo(gastoDiario.add(valorTransferencia)) < 0) {
            throw new LimiteDiarioException("LIMITE DIARIO EXCEDIDO");
        }

    }

    public void saveTransferencia(Transferencia transferencia) {
        repository.save(transferencia);
    }


}
