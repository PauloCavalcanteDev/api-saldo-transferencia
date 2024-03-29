package com.paulodev.apisaldotransferencia.ports.transferencia.impl;

import com.paulodev.apisaldotransferencia.adapters.databases.entities.Transferencia;
import com.paulodev.apisaldotransferencia.adapters.databases.repository.TransferenciaRepository;
import com.paulodev.apisaldotransferencia.exception.LimiteDiarioException;
import com.paulodev.apisaldotransferencia.ports.transferencia.TransferenciaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
        return this.getGastoDiario(contaId);
    }

    @Override
    public void validaLimiteDiario(Long contaId, BigDecimal limiteDiario, BigDecimal valorTransferencia)
            throws LimiteDiarioException {
        log.info("Verificando Gastos Diarios");
        var gastoDiario = this.verificaGastoDiario(contaId);

        if (Objects.nonNull(gastoDiario) && (limiteDiario).compareTo(gastoDiario.add(valorTransferencia)) < 0) {
            log.error("LIMITE DIARIO EXCEDIDO");
            throw new LimiteDiarioException("LIMITE DIARIO EXCEDIDO");
        }

    }

    @Override
    @CacheEvict(value = "GastosDiarios", allEntries = true)
    public void saveTransferencia(Transferencia transferencia) {
        repository.save(transferencia);
    }


    @Cacheable(value = "GastosDiarios", key = "#contaId")
    private BigDecimal getGastoDiario(Long contaId) {
        return repository.getValorDiario(contaId);
    }


}
