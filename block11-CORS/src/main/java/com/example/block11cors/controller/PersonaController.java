package com.example.block11cors.controller;


import com.example.block11cors.Feign.MyFeign;
import com.example.block11cors.application.impl.PersonaServiceImpl;
import com.example.block11cors.controller.dto.Persona.PersonaInputDTO;
import com.example.block11cors.controller.dto.Persona.PersonaOutputDTO;
import com.example.block11cors.controller.dto.Profesor.ProfesorOutputDTO;
import com.example.block11cors.error.EntityNotFoundException;
import com.example.block11cors.error.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonaController {

    @Autowired
    PersonaServiceImpl personaServiceImpl;


    @CrossOrigin(origins = "https://cdpn.io") //no funciona al usar https://codepen.io/ ya que esta no es la dirección del iframe, sino la
    @GetMapping("/getall")
    public Iterable<PersonaOutputDTO> getAllPersonas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {

        return personaServiceImpl.getAllPersonas(pageNumber, pageSize);
    }

    @CrossOrigin(origins = "https://cdpn.io")
    @PostMapping("/addperson")
    ResponseEntity<?> addPersona(@RequestBody PersonaInputDTO persona){
        try {
            personaServiceImpl.addPersona(persona);
            return ResponseEntity.ok().body(persona);
        } catch (UnprocessableEntityException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getCustomError());
        }
    }

}
