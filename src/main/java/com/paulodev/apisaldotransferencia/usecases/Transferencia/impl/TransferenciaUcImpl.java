package com.paulodev.apisaldotransferencia.usecases.Transferencia.impl;

import com.paulodev.apisaldotransferencia.dto.notificaBacen.RetonoBacenDto;
import com.paulodev.apisaldotransferencia.dto.transferencia.SolicitaTransferenciaDto;
import com.paulodev.apisaldotransferencia.usecases.Transferencia.TransferenciaUseCase;
import com.paulodev.apisaldotransferencia.usecases.saldo.impl.ConsultaSaldoUcImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransferenciaUcImpl implements TransferenciaUseCase {
    private final ConsultaSaldoUcImpl consultaSaldoUc;

    public TransferenciaUcImpl(ConsultaSaldoUcImpl consultaSaldoUc) {
        this.consultaSaldoUc = consultaSaldoUc;
    }

    @Override
    public void realizarTransferencia(SolicitaTransferenciaDto transferenciaDto) {
        var saldo = consultaSaldoUc.getSaldo(transferenciaDto.clienteSolicitante(), transferenciaDto.contaOrigem());


        if (transferenciaDto.valor().compareTo(saldo.getSaldo()) > 1) {
            log.info("Saldo Insuficiente");
        }

        this.isContaAtiva();
        this.validaLimiteDiario();
        this.isSaldoSuficiente();
        this.salvarTransferencia();

        this.notificarBacen();

    }


    @Override
    public RetonoBacenDto notificarBacen() {
        log.info("NOTIFICANDO BACEN");
        return null;
    }

    private void identendificarCliente(Long id) {
        log.info("Indentificando Cliente ...{}", id);
    }

    private void validaLimiteDiario() {
        log.info("ValidandoLimite ...");
    }

    private Boolean isSaldoSuficiente() {
        log.info("Verificando Saldo Sufiente ...");
        return true;
    }

    private Boolean isContaAtiva() {
        log.info("Verificando Conta Ativa ...");
        return true;
    }

    private void salvarTransferencia() {
        log.info("Salvando na base de dados ...");

    }


}
