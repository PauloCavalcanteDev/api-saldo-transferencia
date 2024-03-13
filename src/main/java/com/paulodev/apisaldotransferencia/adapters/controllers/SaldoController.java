package com.paulodev.apisaldotransferencia.adapters.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SaldoController {


    @GetMapping("/consulta-saldo")
    public ResponseEntity<String> realizarTransferencia() {
        return ResponseEntity.ok("Saldo atual 1000");
    }
}
