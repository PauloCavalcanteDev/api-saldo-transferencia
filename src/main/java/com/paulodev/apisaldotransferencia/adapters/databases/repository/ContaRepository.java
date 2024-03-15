package com.paulodev.apisaldotransferencia.adapters.databases.repository;

import com.paulodev.apisaldotransferencia.adapters.databases.entities.Conta;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository


public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Transactional
    @Query(value = "SELECT c from Conta c where c.contaId=:contaId and c.clientId=:clientId")
    Optional<Conta> findByIdContaAndClientId(Long contaId, Long clientId);

    @Transactional
    @Query(value = "UPDATE Conta SET saldo=:saldo where contaId=:contaId")
    @Modifying(clearAutomatically = true)
    void trasnferir(BigDecimal saldo, Long contaId);

    @Transactional
    @Query(value = "UPDATE Conta SET saldo=saldo + :deposito where contaId=:contaId")
    @Modifying(clearAutomatically = true)
    void depositar(BigDecimal deposito, Long contaId);
}
