package com.paulodev.apisaldotransferencia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableFeignClients("com.paulodev.apisaldotransferencia")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@Configuration
public class ApiSaldoTransferenciaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiSaldoTransferenciaApplication.class, args);
    }

}
