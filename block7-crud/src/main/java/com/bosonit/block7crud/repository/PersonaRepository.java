package com.bosonit.block7crud.repository;


import com.bosonit.block7crud.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}

