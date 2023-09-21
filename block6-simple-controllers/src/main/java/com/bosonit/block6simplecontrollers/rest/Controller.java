package com.bosonit.block6simplecontrollers.rest;


import com.bosonit.block6simplecontrollers.entity.Persona;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    //recibir persona
    @GetMapping("/user/{nombre}")
    public String getPersona(@PathVariable String nombre){
        return "Hola " + nombre;
    }

    @PostMapping("/useradd")
    public Persona addUser(@RequestBody Persona persona) { //Spring recibe un objeto JSON y Spring Boot lo deserializa en un objeto Persona que recibirá el método por parámetro Persona persona, incrementa la edad en uno y luego devuelve el objeto Persona modificado que SB serializa automáticamente como JSON para la respuesta
        // Incrementar la edad en uno
        persona.setEdad(persona.getEdad() + 1);
        return persona;
    }

}
