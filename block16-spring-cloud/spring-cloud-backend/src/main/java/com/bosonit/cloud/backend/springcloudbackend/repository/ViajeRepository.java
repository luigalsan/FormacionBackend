package com.bosonit.cloud.backend.springcloudbackend.repository;

import com.bosonit.cloud.backend.springcloudbackend.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Integer> {
}
