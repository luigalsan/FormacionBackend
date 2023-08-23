package com.example.block6personcontrollers.rest;

import com.example.block6personcontrollers.entity.Ciudad;
import com.example.block6personcontrollers.entity.Persona;
import com.example.block6personcontrollers.service.CiudadService;
import com.example.block6personcontrollers.service.PersonaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/controlador1")
public class Controlador1 {

    PersonaService servicioPersona;
    CiudadService servicioCiudad;

    public Controlador1(PersonaService servicioPersona, CiudadService servicioCiudad){ //Inyección de dependencias servicios mediante constructor
        this.servicioPersona = servicioPersona;
        this.servicioCiudad = servicioCiudad;
    }

    @GetMapping("/addPersona")
    public Persona getPersona(
            @RequestHeader String nombre,
            @RequestHeader String poblacion,
            @RequestHeader int edad){

        return servicioPersona.crearPersona(nombre, poblacion, edad);
    }

    @PostMapping("/addCiudad")
    public void addCiudad(@RequestBody Ciudad ciudad){
        servicioCiudad.addCiudad(ciudad);
    }

    @GetMapping("/getCiudades")
    public List<Ciudad> getCiudad(){
        return servicioCiudad.getCiudades();
    }


}


