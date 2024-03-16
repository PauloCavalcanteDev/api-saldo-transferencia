package com.paulodev.apisaldotransferencia.usecases.Transferencia.impl;

import com.paulodev.apisaldotransferencia.adapters.databases.entities.Conta;
import com.paulodev.apisaldotransferencia.adapters.validador.ValidadorConta;
import com.paulodev.apisaldotransferencia.dto.notificaBacen.NotificabacenDto;
import com.paulodev.apisaldotransferencia.dto.transferencia.ResponseTransferenciaDto;
import com.paulodev.apisaldotransferencia.dto.transferencia.SolicitaTransferenciaDto;
import com.paulodev.apisaldotransferencia.enums.StatusEnum;
import com.paulodev.apisaldotransferencia.enums.StatusTransacao;
import com.paulodev.apisaldotransferencia.enums.TipoTransacao;
import com.paulodev.apisaldotransferencia.exception.ContaInativaException;
import com.paulodev.apisaldotransferencia.exception.ErroBuscarClienteException;
import com.paulodev.apisaldotransferencia.exception.LimiteDiarioException;
import com.paulodev.apisaldotransferencia.ports.api.DadosClienteService;
import com.paulodev.apisaldotransferencia.ports.api.impl.IntegracaoBacenService;
import com.paulodev.apisaldotransferencia.ports.conta.ContaService;
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
    private final ValidadorConta validadorConta;
    private final IntegracaoBacenService integracaoBacenService;


    @Autowired
    public TransferenciaUcImpl(DadosClienteService clienteService, ContaService contaService, ValidadorConta validadorConta, IntegracaoBacenService integracaoBacenService) {
        this.clienteService = clienteService;
        this.contaService = contaService;
        this.validadorConta = validadorConta;
        this.integracaoBacenService = integracaoBacenService;
    }

    @Override
    public ResponseTransferenciaDto realizarTransferencia(SolicitaTransferenciaDto transferenciaDto) throws ErroBuscarClienteException {
        var dadosContaOrigem = this.contaService.buscaConta(transferenciaDto.contaOrigem());
        var nomeCliente = this.clienteService.buscaDadosCliente(transferenciaDto.clienteSolicitante()).nome();

        try {
            this.validadorConta.contaAtiva(dadosContaOrigem.isContaAtiva());
            this.validadorConta.validaLimiteDiario(dadosContaOrigem.getLimiteDiario(), transferenciaDto.valor());
            this.integracaoBacenService.notificaBacen(this.getNotificadorBacen());
            return this.transferir(dadosContaOrigem, transferenciaDto.contaDestino(), transferenciaDto.valor());

        } catch (ContaInativaException e) {
            return this.setTransferenciaNaoRealizada(e.getMessage(), StatusEnum.ERRO);
        } catch (LimiteDiarioException e) {
            return this.setTransferenciaNaoRealizada(e.getMessage(), StatusEnum.ERRO);
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
        if (contaOrigem.getSaldo().compareTo(deposito) < 0) {
            return this.setTransferenciaNaoRealizada("TRANFERENCIA NÂO REALIZADA SALDO INSUFICIENTE", StatusEnum.SALDO_INSUFICIENTE);
        }
        try {

            contaService.retirarSaldo(contaOrigem.getSaldo().subtract(deposito), contaOrigem.getContaId());
            contaService.depositar(deposito, contaDestino);
        } catch (Exception ex) {
            return this.setTransferenciaNaoRealizada(ex.getMessage(), StatusEnum.ERRO);
        }

        return setTransferenciaNaoRealizada("TRANSFERENCIA REALIZADA COM SUCESSO", StatusEnum.REALIZADA);
    }


    private ResponseTransferenciaDto setTransferenciaNaoRealizada(String mensagem, StatusEnum status) {
        return new ResponseTransferenciaDto(
                LocalDateTime.now(),
                status,
                mensagem

        );
    }

}
