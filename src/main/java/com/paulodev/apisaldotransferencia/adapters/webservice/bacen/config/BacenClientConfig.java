package com.paulodev.apisaldotransferencia.adapters.webservice.bacen.config;

import com.paulodev.apisaldotransferencia.adapters.webservice.buscarCliente.config.DadosClienteErroDecoder;
import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;

public class BacenClientConfig {

    @Bean
    public DadosClienteErroDecoder error() {
        return new DadosClienteErroDecoder();
    }

    @Bean
    Retryer retryer() {
        return new Retryer.Default(3000, 9000, 3);
    }

    @Bean
    Logger.Level feingLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
