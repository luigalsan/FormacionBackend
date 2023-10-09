package com.bosonit.blockcrudvalidation.repository;

import com.bosonit.blockcrudvalidation.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> { //Primer parámetro tipo objeto a mapear a la base de datos, segundo la clase que se usa como primary key
}
