package com.example.block7crudvalidation.controller;


import com.example.block7crudvalidation.application.impl.PersonaServiceImpl;
import com.example.block7crudvalidation.controller.dto.Persona.PersonaInputDTO;
import com.example.block7crudvalidation.controller.dto.Persona.PersonaOutputDTO;
import com.example.block7crudvalidation.error.EntityNotFoundException;
import com.example.block7crudvalidation.error.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaServiceImpl personaServiceImpl;

    @GetMapping("/{id}")
    public ResponseEntity<?> findPersonById(@PathVariable Integer id) {
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

        return personaServiceImpl.getAllPersonas(pageNumber, pageSize);
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


//    //CAMBIAR PORQUE NO USO EL UPDATE
//    @PutMapping
//    public ResponseEntity<?> updatePersona(@RequestBody PersonaInputDTO persona) {
//        try {
//            personaServiceImpl.getPersonaById(persona.getId_persona()); //Obtengo el Id del objeto persona en POJO previamente serializado desde un JSON
//            return ResponseEntity.ok().body(personaServiceImpl.addPersona(persona));
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersona(@PathVariable Integer id) {
        try {
            personaServiceImpl.deletePersonaById(id);
            return ResponseEntity.ok().body("El usuario con id " + id + " ha sido eliminada correctamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }
}
