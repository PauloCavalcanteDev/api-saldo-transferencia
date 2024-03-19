package com.paulodev.apisaldotransferencia.adapters.webservice.buscarCliente.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;

public class DadosClienteConfig {

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
