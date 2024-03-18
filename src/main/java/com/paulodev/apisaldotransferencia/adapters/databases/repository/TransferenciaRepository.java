package com.paulodev.apisaldotransferencia.adapters.databases.repository;

import com.paulodev.apisaldotransferencia.adapters.databases.entities.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {


    @Query(value = "SELECT sum(valor) from Transferencia t where timestamp >= CURRENT_DATE and contaOrigem =:contaId ")
    BigDecimal getValorDiario(@Param("contaId") Long contaId);
}
