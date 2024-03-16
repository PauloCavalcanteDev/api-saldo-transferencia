package com.paulodev.apisaldotransferencia.dto;


import java.io.Serializable;

public record ClienteDto(Integer id, Long cliente_id, String nome ) implements Serializable {
}