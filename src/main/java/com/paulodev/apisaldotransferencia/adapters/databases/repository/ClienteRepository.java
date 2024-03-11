package com.paulodev.apisaldotransferencia.adapters.databases.repository;

import com.paulodev.apisaldotransferencia.adapters.databases.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
