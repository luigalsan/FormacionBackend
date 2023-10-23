package com.bosonit.cloud.backend.springcloudbackend.repository;

import com.bosonit.cloud.backend.springcloudbackend.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
