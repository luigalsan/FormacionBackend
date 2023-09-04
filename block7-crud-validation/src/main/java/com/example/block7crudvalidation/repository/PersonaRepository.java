package com.example.block7crudvalidation.repository;

import com.example.block7crudvalidation.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> { //Primer parámetro tipo objeto a mapear a la base de datos, segundo la clase que se usa como primary key
}
