package com.paulodev.apisaldotransferencia.adapters.controllers;

import com.paulodev.apisaldotransferencia.dto.transferencia.ResponseTransferenciaDto;
import com.paulodev.apisaldotransferencia.dto.transferencia.SolicitaTransferenciaDto;
import com.paulodev.apisaldotransferencia.exception.ContaInvalidaExption;
import com.paulodev.apisaldotransferencia.usecases.Transferencia.TransferenciaUseCase;
import com.paulodev.apisaldotransferencia.usecases.saldo.impl.ConsultaSaldoUcImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/operacoes")
public class OperacoesController {

    private final ConsultaSaldoUcImpl consultaSaldoUsecase;
    private final TransferenciaUseCase transferenciaUseCase;


    @Autowired
    public OperacoesController(ConsultaSaldoUcImpl consultaSaldoUsecase, TransferenciaUseCase transferenciaUseCase) {
        this.consultaSaldoUsecase = consultaSaldoUsecase;
        this.transferenciaUseCase = transferenciaUseCase;
    }

    @GetMapping("/consulta-saldo/{idClient}/{idConta}")
    public ResponseEntity realizarTransferencia(@PathVariable("idClient") Long cliente, @PathVariable("idConta") Long contaId) {
        try {
            return ResponseEntity.ok(consultaSaldoUsecase.getSaldo(cliente, contaId));
        } catch (ContaInvalidaExption ex) {
            return ResponseEntity.notFound().build();

        }
    }

    @PostMapping("/transferencia")
    public ResponseEntity<ResponseTransferenciaDto> transferir(@RequestBody SolicitaTransferenciaDto soclicitacaoTransferencia) {
        try {

            return ResponseEntity.ok(transferenciaUseCase.realizarTransferencia(soclicitacaoTransferencia));
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("APP IS RUNNING");
    }
}
