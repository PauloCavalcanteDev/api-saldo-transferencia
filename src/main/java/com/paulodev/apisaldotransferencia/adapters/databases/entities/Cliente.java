package com.paulodev.apisaldotransferencia.adapters.databases.entities;


import jakarta.persistence.*;
import lombok.*;


@Table(name = "CLIENTES")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cliente {
    @Id
    @Column(name = "cliente_id", nullable = false)
    private Long clientId;
    @Column(name = "conta_id")
    private Long contaId;
}
