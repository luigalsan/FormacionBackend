package com.example.block6personcontrollers.rest;


import com.example.block6personcontrollers.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.ldap.Control;

@RestController
public class Controlador2 {

    Controlador1 personaCreada;

    @Autowired
    public Controlador2(Controlador1 personaCreada){ //Debido a que Controlador1 se guarda en el contexto de spring al heredar @RestController de @Component, puedo inyectarlo en Controlador2
        this.personaCreada = personaCreada;
    }

    @GetMapping("/controlador2/getPersona")
    public Persona getPersona(){

        return personaCreada.getPersonaControlador1(); //Metodo sobreescrito

    }

}
