package com.paulodev.apisaldotransferencia.adapters.controllers;

import com.paulodev.apisaldotransferencia.dto.SaldoDto;
import com.paulodev.apisaldotransferencia.usecases.saldo.impl.ConsultaSaldoUsecaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/operacoes")
public class OperacoesController {

    private final ConsultaSaldoUsecaseImpl consultaSaldoUsecase;

    @Autowired
    public OperacoesController(ConsultaSaldoUsecaseImpl consultaSaldoUsecase) {
        this.consultaSaldoUsecase = consultaSaldoUsecase;
    }

    @GetMapping("/consulta-saldo/{idClient}/{idConta}")
    public ResponseEntity<SaldoDto> realizarTransferencia(@PathVariable("idClient") Long cliente,
                                                          @PathVariable("idConta") Long conta) {
        return ResponseEntity.ok(consultaSaldoUsecase.getSaldo(cliente, conta));

    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("APP IS RUNNING");
    }
}
