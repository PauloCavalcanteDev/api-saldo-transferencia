package com.paulodev.apisaldotransferencia.adapters.controllers;

import com.paulodev.apisaldotransferencia.dto.TransferenciaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TransferenciaController {

    @PostMapping("/transferencia")
    public ResponseEntity<TransferenciaDto> realizarTransferencia(@RequestBody TransferenciaDto dto) {
        return ResponseEntity.ok(dto);
    }

}
