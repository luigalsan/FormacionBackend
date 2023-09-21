package com.bosonit.block6personcontrollers.service;


import org.springframework.stereotype.Service;
import com.bosonit.block6personcontrollers.entity.Persona;
@Service
public class PersonaService {
    public Persona crearPersona(String nombre, String poblacion, int edad){

        return new Persona(nombre, poblacion, edad);
    }

}
