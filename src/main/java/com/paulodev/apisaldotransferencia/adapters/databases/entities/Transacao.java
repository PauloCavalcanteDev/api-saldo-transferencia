package com.paulodev.apisaldotransferencia.adapters.databases.entities;

import com.paulodev.apisaldotransferencia.core.Tipo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Table(name = "TRANSACOES")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Transacao {
    @Id
    @Column(name = "", nullable = false)
    private Long idTransacao;
    @Column(name = "conta_id", nullable = false)
    private Long contaId;
    @Column(name = "cliente_id", nullable = false)
    private Long clientId;
    @Column(name = "tipo")
    private Enum<Tipo> tipo;
    private Date timestamp;


}
