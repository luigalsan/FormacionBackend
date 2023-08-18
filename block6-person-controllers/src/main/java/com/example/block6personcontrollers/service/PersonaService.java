package com.example.block6personcontrollers.service;


import org.springframework.stereotype.Service;
import com.example.block6personcontrollers.Persona;
@Service
public class PersonaService {

    Persona persona;

    public Persona crearPersona(String nombre, String poblacion, int edad){

        Persona persona = new Persona();
        persona.setNombre(nombre);
        persona.setPoblacion(poblacion);
        persona.setEdad(edad);

            return persona;
    }

    public Persona getPersona(){
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }


}
