package com.paulodev.apisaldotransferencia.adapters.databases.entities;

import com.paulodev.apisaldotransferencia.enums.StatusTransacao;
import com.paulodev.apisaldotransferencia.enums.TipoTransacao;
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
    @Column(name = "id_transacao", nullable = false)
    private Long idTransacao;

    @Column(name = "conta_orig", nullable = false)
    private Long contaOrigem;

    @Column(name = "conta_dstn", nullable = false)
    private Long contaDestino;

    @Column(name = "cliente_id", nullable = false)
    private Long clientId;

    @Column(name = "tipo_trsc")
    private Enum<TipoTransacao> tipo;

    @Column(name = "data_hora")
    private Date timestamp;

    @Column(name = "status_bacen", nullable = false)
    private Enum<StatusTransacao> status;


}
