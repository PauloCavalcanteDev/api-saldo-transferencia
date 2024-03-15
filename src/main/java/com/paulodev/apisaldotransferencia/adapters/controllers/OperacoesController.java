package com.paulodev.apisaldotransferencia.adapters.controllers;

import com.paulodev.apisaldotransferencia.dto.SaldoDto;
import com.paulodev.apisaldotransferencia.dto.transferencia.ResponseTransferenciaDto;
import com.paulodev.apisaldotransferencia.dto.transferencia.SolicitaTransferenciaDto;
import com.paulodev.apisaldotransferencia.usecases.Transferencia.TransferenciaUseCase;
import com.paulodev.apisaldotransferencia.usecases.saldo.impl.ConsultaSaldoUcImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/operacoes")
public class OperacoesController {

    private final ConsultaSaldoUcImpl consultaSaldoUsecase;
    private final TransferenciaUseCase transferencia;

    @Autowired
    public OperacoesController(ConsultaSaldoUcImpl consultaSaldoUsecase, TransferenciaUseCase transferencia) {
        this.consultaSaldoUsecase = consultaSaldoUsecase;
        this.transferencia = transferencia;
    }

    @GetMapping("/consulta-saldo/{idClient}/{idConta}")
    public ResponseEntity<SaldoDto> realizarTransferencia(@PathVariable("idClient") Long cliente, @PathVariable("idConta") Long contaId) {
        return ResponseEntity.ok(consultaSaldoUsecase.getSaldo(cliente, contaId));
    }

    @PostMapping("/transferencia")
    public ResponseEntity<ResponseTransferenciaDto> transferir(@RequestBody SolicitaTransferenciaDto soclicitacaoTransferencia) {
        return ResponseEntity.ok(transferencia.realizarTransferencia(soclicitacaoTransferencia));
    }


    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("APP IS RUNNING");
    }
}
