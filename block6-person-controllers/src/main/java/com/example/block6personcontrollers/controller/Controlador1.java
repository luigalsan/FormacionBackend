package com.example.block6personcontrollers.controller;

import com.example.block6personcontrollers.service.PersonaService;
import com.example.block6personcontrollers.Persona;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {
    private final PersonaService personaService;

    @Autowired
    public Controlador1(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/addPersona")
    public ResponseEntity<Persona> addPersona( @RequestHeader String nombre, @RequestHeader String poblacion, @RequestHeader int edad) {
                Persona persona = personaService.crearPersona(nombre, poblacion, edad);
                personaService.setPersona(persona);
                return ResponseEntity.ok(persona);
            }
}



