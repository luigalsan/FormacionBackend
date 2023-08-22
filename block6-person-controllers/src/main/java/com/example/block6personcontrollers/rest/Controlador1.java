package com.example.block6personcontrollers.rest;

import com.example.block6personcontrollers.entity.Persona;
import com.example.block6personcontrollers.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controlador1 {

    Persona personaControlador1;

    @Autowired
    PersonaService servicio;

    public Controlador1(PersonaService servicio){ //Inyección de dependencia servicio mediante constructor
        this.servicio = servicio;

    }

    @GetMapping("/controlador1/addPersona")
    public Persona getPersona(
            @RequestHeader String nombre,
            @RequestHeader String poblacion,
            @RequestHeader int edad){

        personaControlador1 = servicio.crearPersona(nombre, poblacion, edad);

        return personaControlador1;
    }

    public Persona getPersonaControlador1(){

        return personaControlador1;
    }

}


