package com.example.block6simplecontrollers.rest;


import com.example.block6simplecontrollers.entity.Persona;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;


@RestController
public class Controller {

    private Persona persona;

    /*Defino esta etiqueta para cargar los datos de persona solo una vez. Este método será ejecutado
     después de la inyeccion de dependencias*/
    @PostConstruct
    public void cargarPersona(){

        //Crear objeto Persona con valores
        persona = new Persona("Pepito", 20, "Almería");
    }

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
