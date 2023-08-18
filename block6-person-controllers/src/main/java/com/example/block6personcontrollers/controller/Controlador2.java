package com.example.block6personcontrollers.controller;

import com.example.block6personcontrollers.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.block6personcontrollers.Persona;
@RestController
@RequestMapping("/controlador2")
public class Controlador2 {
    private final PersonaService personaService;

    @Autowired
    public Controlador2(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/getPersona")
    public ResponseEntity<Persona> getPersona() {
        Persona persona = personaService.getPersona(); // Obtiene la persona almacenada
        persona.setEdad(persona.getEdad() * 2);
        return ResponseEntity.ok(persona);
    }
}