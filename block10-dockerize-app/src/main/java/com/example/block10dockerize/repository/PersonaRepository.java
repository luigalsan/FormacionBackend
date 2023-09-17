package com.example.block10dockerize.repository;

import com.example.block10dockerize.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long>{

}