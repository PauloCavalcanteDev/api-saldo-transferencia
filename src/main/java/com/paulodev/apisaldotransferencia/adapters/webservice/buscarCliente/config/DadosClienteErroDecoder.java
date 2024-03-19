package com.paulodev.apisaldotransferencia.adapters.webservice.buscarCliente.config;

import com.paulodev.apisaldotransferencia.exception.NotFoundException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;

public class DadosClienteErroDecoder implements ErrorDecoder {
    @SneakyThrows
    @Override
    public Exception decode(String metodo, Response response) {
        switch (response.status()) {
            case 401:
                return new BadRequestException("DADOS INVALIDOS");
            case 404:
                return new NotFoundException("CLIENTE NÂO ENCONTRADO");
            default:
                return new RetryableException(response.status(), "String message", response.request().httpMethod(), 3L, response.request());

        }
    }
}
