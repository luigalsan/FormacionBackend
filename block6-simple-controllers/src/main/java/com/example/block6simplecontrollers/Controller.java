package com.example.block6simplecontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    Persona persona;
    @Autowired
    public Controller(Persona persona){
        this.persona=persona;
    }

    @GetMapping("/user/{nombre}")
    public String nombre(@PathVariable String nombre){
        return "Hola " + nombre;
    }

    @PostMapping("/useradd")
    public Persona persona(@RequestBody Persona persona){ // Las claves del JSON en postman deben coincidir
                                                          // con los valores de campo para poder deserializar
        int nuevaEdad = persona.getEdad() + 1;
        persona.setEdad(nuevaEdad);
        System.out.println("La edad de la persona ahora es: " + persona.getEdad());
        return persona;
    }

}
