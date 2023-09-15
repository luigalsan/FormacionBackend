package com.example.demoPostgre.repository;

import com.example.demoPostgre.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long>{

}