package com.paulodev.apisaldotransferencia.adapters.webservice.bacen.config;

import com.paulodev.apisaldotransferencia.exception.NotFoundException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;

@Slf4j
public class BacenClientErroDecoder implements ErrorDecoder {
    @SneakyThrows
    @Override
    public Exception decode(String metodo, Response response) {
        switch (response.status()) {
            case 401:
                return new BadRequestException("DADOS INVALIDOS");
            case 404:
                return new NotFoundException("CLIENTE NÂO ENCONTRADO");
            case 429:
                //TODO Implantar a chamada para o SQS
                log.info("ENVIAR MENSAGEM PARA O SQS PARA CONSUMIR EM RETENTATIVA!");
            default:
                return new RetryableException(response.status(), "String message", response.request().httpMethod(), 3L, response.request());

        }
    }
}
