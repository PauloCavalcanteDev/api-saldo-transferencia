package com.paulodev.apisaldotransferencia.adapters.databases.repository;

import com.paulodev.apisaldotransferencia.adapters.databases.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
}
