package com.example.block7crudvalidation.controller;


import com.example.block7crudvalidation.application.PersonaServiceImpl;
import com.example.block7crudvalidation.controller.dto.PersonaInputDTO;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDTO;
import com.example.block7crudvalidation.error.EntityNotFoundException;
import com.example.block7crudvalidation.error.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class Controller {

    @Autowired
    PersonaServiceImpl personaServiceImpl;

    @GetMapping("/{id}")
    public ResponseEntity<?> findPersonById(@PathVariable int id) {
        try {
            PersonaOutputDTO persona = personaServiceImpl.getPersonaById(id);
            return ResponseEntity.ok().body(persona);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<?> findPersonByUsuario(@PathVariable String usuario) {
        try {
            return ResponseEntity.ok().body(personaServiceImpl.getPersonaByUsuario(usuario));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }

    @GetMapping
    public Iterable<PersonaOutputDTO> getAllStudents(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {

        return personaServiceImpl.getAllStudents(pageNumber, pageSize);
    }

    @PostMapping()
    ResponseEntity<?> addPersona(@RequestBody PersonaInputDTO persona){
        try {
            personaServiceImpl.addPersona(persona);
            return ResponseEntity.ok().body(persona);
        } catch (UnprocessableEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }

    @PutMapping
    public ResponseEntity<?> updatePersona(@RequestBody PersonaInputDTO persona) {
        try {
            personaServiceImpl.getPersonaById(persona.getId()); //Obtengo el Id del objeto persona en POJO previamente serializado desde un JSON
            return ResponseEntity.ok().body(personaServiceImpl.addPersona(persona));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersona(@PathVariable int id) {
        try {
            personaServiceImpl.deletePersonaById(id);
            return ResponseEntity.ok().body("La persona con el " + id + "ha sido eliminada correctamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }
}
