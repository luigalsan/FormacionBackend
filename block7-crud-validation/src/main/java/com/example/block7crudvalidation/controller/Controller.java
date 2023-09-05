package com.example.block7crudvalidation.controller;


import com.example.block7crudvalidation.application.PersonaServiceImpl;
import com.example.block7crudvalidation.controller.dto.PersonaInputDTO;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/persona")
public class Controller {

    @Autowired
    PersonaServiceImpl personaServiceImpl;

    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDTO> findPersonById(@PathVariable int id){
        try {
            return ResponseEntity.ok().body(personaServiceImpl.getPersonaById(id));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<PersonaOutputDTO> findPersonByUsuario(@PathVariable String usuario){
        try{
            return ResponseEntity.ok().body(personaServiceImpl.getPersonaByUsuario(usuario));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping()
    PersonaOutputDTO addPersona(@RequestBody PersonaInputDTO persona) throws Exception {
            return personaServiceImpl.addPersona(persona);
    }
    @GetMapping
    public Iterable<PersonaOutputDTO> getAllStudents(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {

        return personaServiceImpl.getAllStudents(pageNumber, pageSize);
    }

}
