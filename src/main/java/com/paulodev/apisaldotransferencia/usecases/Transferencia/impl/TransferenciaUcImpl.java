package com.paulodev.apisaldotransferencia.usecases.Transferencia.impl;

import com.paulodev.apisaldotransferencia.adapters.databases.entities.Conta;
import com.paulodev.apisaldotransferencia.conveters.TransferenciaConverter;
import com.paulodev.apisaldotransferencia.dto.notificaBacen.NotificabacenDto;
import com.paulodev.apisaldotransferencia.dto.transferencia.ResponseTransferenciaDto;
import com.paulodev.apisaldotransferencia.dto.transferencia.SolicitaTransferenciaDto;
import com.paulodev.apisaldotransferencia.enums.StatusEnum;
import com.paulodev.apisaldotransferencia.enums.StatusTransacao;
import com.paulodev.apisaldotransferencia.enums.TipoTransacao;
import com.paulodev.apisaldotransferencia.exception.ContaInativaException;
import com.paulodev.apisaldotransferencia.exception.ErroTransferenciaException;
import com.paulodev.apisaldotransferencia.exception.LimiteDiarioException;
import com.paulodev.apisaldotransferencia.ports.api.DadosClienteService;
import com.paulodev.apisaldotransferencia.ports.api.impl.IntegracaoBacenService;
import com.paulodev.apisaldotransferencia.ports.conta.ContaService;
import com.paulodev.apisaldotransferencia.ports.transferencia.impl.TransferenciaServiceImpl;
import com.paulodev.apisaldotransferencia.usecases.Transferencia.TransferenciaUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@Slf4j
public class TransferenciaUcImpl implements TransferenciaUseCase {

    private final DadosClienteService clienteService;
    private final ContaService contaService;
    private final TransferenciaServiceImpl transferenciaService;
    private final IntegracaoBacenService integracaoBacenService;
    private final TransferenciaConverter transferenciaConverter;


    @Autowired
    public TransferenciaUcImpl(DadosClienteService clienteService,
                               ContaService contaService,
                               TransferenciaServiceImpl transferenciaService,
                               IntegracaoBacenService integracaoBacenService, TransferenciaConverter transferenciaConverter) {
        this.clienteService = clienteService;
        this.contaService = contaService;
        this.transferenciaService = transferenciaService;

        this.integracaoBacenService = integracaoBacenService;
        this.transferenciaConverter = transferenciaConverter;
    }

    @Override
    public ResponseTransferenciaDto realizarTransferencia(SolicitaTransferenciaDto dto) throws ErroTransferenciaException {
        log.info("Iniciando Transferencia -> Origiem : {} - destino:  {} - valor: {} ",
                dto.clienteSolicitante(),
                dto.contaDestino(),
                dto.valor()
        );
        try {
            var dadosContaOrigem = this.contaService.buscaConta(dto.contaOrigem());
            var nomeCliente = this.clienteService.buscaDadosCliente(dto.clienteSolicitante()).nome();
            this.isContaAtiva(dadosContaOrigem.isContaAtiva());

            this.transferenciaService.validaLimiteDiario(
                    dto.contaOrigem(),
                    dadosContaOrigem.getLimiteDiario(),
                    dto.valor()
            );

            var transferenciaRealizada = this.transferir(dadosContaOrigem, dto.contaDestino(), dto.valor());
            transferenciaService.saveTransferencia(transferenciaConverter.dtoToTransferencia(dto));
            this.integracaoBacenService.notificaBacen(this.getNotificadorBacen());

            return transferenciaRealizada;


        } catch (ContaInativaException e) {
            return this.setTransferencia(e.getMessage(), StatusEnum.ERRO);
        } catch (LimiteDiarioException e) {
            return this.setTransferencia(e.getMessage(), StatusEnum.ERRO);
        }
    }

    private NotificabacenDto getNotificadorBacen() {
        return new NotificabacenDto(
                1L,
                10L,
                20L,
                200L,
                TipoTransacao.TRANSFERENCIA,
                LocalDateTime.now(),
                StatusTransacao.REALIZDA);
    }

    private ResponseTransferenciaDto transferir(Conta contaOrigem, Long contaDestino, BigDecimal deposito) {
        log.info("Realizando Transferencia origem :{}, destino: {}, valor: {}",
                contaOrigem,
                contaDestino,
                deposito);
        if (contaOrigem.getSaldo().compareTo(deposito) < 0) {
            log.error("\"TRANFERENCIA NÂO REALIZADA SALDO INSUFICIENTE");
            return this.setTransferencia("TRANFERENCIA NÂO REALIZADA SALDO INSUFICIENTE", StatusEnum.SALDO_INSUFICIENTE);
        }
        try {
            contaService.retirarSaldo(contaOrigem.getSaldo().subtract(deposito), contaOrigem.getContaId());
            contaService.depositar(deposito, contaDestino);
        } catch (Exception ex) {
            log.error("ERROR -> {}", ex.getMessage());
            return this.setTransferencia(ex.getMessage(), StatusEnum.ERRO);
        }
        log.info("Transferencia Realizada com Sucesso!");

        return setTransferencia("TRANSFERENCIA REALIZADA COM SUCESSO", StatusEnum.REALIZADA);
    }

    private void isContaAtiva(boolean contaAtiva) throws ContaInativaException {
        log.info("Validando Conta Ativa");
        if (!contaAtiva) {
            log.error("CONTA INATIVA");
            throw new ContaInativaException("CONTA INATIVA");
        }

    }


    private ResponseTransferenciaDto setTransferencia(String mensagem, StatusEnum status) {
        return new ResponseTransferenciaDto(
                LocalDateTime.now(),
                status,
                mensagem

        );
    }

}
