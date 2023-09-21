package com.bosonit.block6personcontrollers.rest;


import com.bosonit.block6personcontrollers.entity.Ciudad;
import com.bosonit.block6personcontrollers.entity.Persona;
import com.bosonit.block6personcontrollers.service.CiudadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controlador2 {

    CiudadService servicioCiudad;

    public Controlador2 (CiudadService servicioCiudad) { //Esto es singleton???
        this.servicioCiudad = servicioCiudad;
    }
    @GetMapping("/getPersona")
    public Persona obtenerPersonaMultiplicada(@RequestBody Persona persona){ //Esta etiqueta ya instancia persona
        persona.setEdad(persona.getEdad() * 2);
        return persona;

    }
    @GetMapping("/getCiudades")
    public List<Ciudad> getListaCiudades(){

        return servicioCiudad.getCiudades();
    }

}
