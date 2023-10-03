package com.bosonit.block7crudvalidation.controller;


import com.bosonit.block7crudvalidation.Feign.MyFeign;
import com.bosonit.block7crudvalidation.application.impl.PersonaServiceImpl;
import com.bosonit.block7crudvalidation.controller.dto.Persona.PersonaInputDTO;
import com.bosonit.block7crudvalidation.controller.dto.Persona.PersonaOutputDTO;
import com.bosonit.block7crudvalidation.controller.dto.Profesor.ProfesorOutputDTO;
import com.bosonit.block7crudvalidation.error.EntityNotFoundException;
import com.bosonit.block7crudvalidation.error.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaServiceImpl personaServiceImpl;

    @Autowired
    MyFeign feignService;

    @GetMapping("/profesor/{id}")
    public ResponseEntity<ProfesorOutputDTO> getProfesorById(
            @PathVariable Integer id){
        try{
            ProfesorOutputDTO profesorOutputDTO = feignService.getProfesorById(id);

            if (profesorOutputDTO != null){
                return ResponseEntity.ok().body(profesorOutputDTO);
            }else {
                throw new EntityNotFoundException("No se encuentra el profesor con la id: " + id);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPersonById(@PathVariable Integer id, @RequestParam(value = "outputType", defaultValue = "default") String outputType) {
        try{
            return ResponseEntity.ok().body(personaServiceImpl.getPersonaId(id,outputType));
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<?> findPersonByUsuario(@PathVariable String usuario, @RequestParam(value = "outputType", defaultValue = "default") String outputType) {
        try{
            return ResponseEntity.ok().body(personaServiceImpl.getPersonaByUsuario(usuario,outputType));
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }

    @GetMapping
    public Iterable<PersonaOutputDTO> getAllPersonas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {

        return personaServiceImpl.getAllPersonas(pageNumber, pageSize);
    }

    @PostMapping
    ResponseEntity<?> addPersona(@RequestBody PersonaInputDTO persona){
        try {
            personaServiceImpl.addPersona(persona);
            return ResponseEntity.ok().body(persona);
        } catch (UnprocessableEntityException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getCustomError());
        }
    }


    @PutMapping
    public ResponseEntity<?> updatePersona(@RequestBody PersonaInputDTO persona) {
        try {
            personaServiceImpl.getPersonaId(persona.getId_persona(),"default"); //Obtengo el Id del objeto persona en POJO previamente serializado desde un JSON
            return ResponseEntity.ok().body(personaServiceImpl.updatePersona(persona));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersona(@PathVariable Integer id) {
        try {
            personaServiceImpl.deletePersonaById(id);
            return ResponseEntity.ok().body("El usuario con id: " + id + " ha sido eliminada correctamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }

}
