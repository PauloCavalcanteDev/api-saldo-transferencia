package com.paulodev.apisaldotransferencia.adapters.databases.repository;

import com.paulodev.apisaldotransferencia.adapters.databases.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {


    @Query(value = "SELECT c from Conta c where c.contaId=:contaId and c.clientId=:clientId")
    Optional<Conta> findByIdContaAndClientId(Long contaId, Long clientId);
}
