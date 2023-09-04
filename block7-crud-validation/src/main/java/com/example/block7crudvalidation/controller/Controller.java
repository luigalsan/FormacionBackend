package com.example.block7crudvalidation.controller;


import com.example.block7crudvalidation.application.PersonaServiceImpl;
import com.example.block7crudvalidation.controller.dto.PersonaInputDTO;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/persona")
public class Controller {

    @Autowired
    PersonaServiceImpl personaServiceImpl;

    @GetMapping("/{id}")
    PersonaOutputDTO getPersonById(@PathVariable int id){
        try{
            return personaServiceImpl.getPersonaById(id);
        }catch(NoSuchElementException e){
            System.out.println("No se ha encontrado el elemento");
        }
        return null;
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
