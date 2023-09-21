package com.bosonit.block7crud.rest;


import com.bosonit.block7crud.service.PersonaServiceImpl;
import com.bosonit.block7crud.rest.dto.PersonaInputDTO;
import com.bosonit.block7crud.rest.dto.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/persona")
public class Controller {

    @Autowired
    PersonaServiceImpl personServiceImpl;

    @PostMapping
    public ResponseEntity<PersonaOutputDTO> addPersona(@RequestBody PersonaInputDTO persona){
        URI location = URI.create("/persona");

        return ResponseEntity.created(location).body(personServiceImpl.addPersona(persona));
    }
    @PutMapping
    public ResponseEntity<PersonaOutputDTO> updatePersona(@RequestBody PersonaInputDTO persona){
        try {
            personServiceImpl.getPersonaById(persona.getId()); //Obtengo el Id del objeto persona en POJO previamente serializado desde un JSON
            return  ResponseEntity.ok().body(personServiceImpl.updatePersona(persona));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonaOutputDTO> deletePersona(@PathVariable int id){
        try{
            PersonaOutputDTO personaEncontrada = personServiceImpl.getPersonaById(id);
            personServiceImpl.deletePersonaById(id);
            return ResponseEntity.ok().body(personaEncontrada);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDTO> getPersonaById(@PathVariable int id){
        try{
            return ResponseEntity.ok().body(personServiceImpl.getPersonaById(id));

        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Iterable<PersonaOutputDTO> getAllStudents(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "2", required = false) int pageSize) {

        return personServiceImpl.getAllPersonas(pageNumber, pageSize);
    }

}





