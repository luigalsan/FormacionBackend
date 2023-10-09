package com.bosonit.blockcrudvalidation.controller;

import com.bosonit.blockcrudvalidation.application.impl.ProfesorServiceImpl;
import com.bosonit.blockcrudvalidation.application.impl.StudentServiceImpl;
import com.bosonit.blockcrudvalidation.controller.dto.Profesor.ProfesorInputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Profesor.ProfesorOutputDTO;
import com.bosonit.blockcrudvalidation.error.EntityNotFoundException;
import com.bosonit.blockcrudvalidation.error.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {

    @Autowired
    ProfesorServiceImpl profesorService;

    @Autowired
    StudentServiceImpl studentService;

    //
    @PostMapping
    public ResponseEntity<ProfesorOutputDTO> addProfesor(@RequestBody ProfesorInputDTO profesorInputDTO){
        try{
           return ResponseEntity.ok().body(profesorService.addProfesor(profesorInputDTO));

        }catch(UnprocessableEntityException u){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, u.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProfesorOutputDTO> getProfesorById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok().body(profesorService.getProfesorById(id));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());         }
    }
    @GetMapping
    public Iterable<ProfesorOutputDTO> getAllProfesores(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {

        return profesorService.getAllProfesor(pageNumber, pageSize);
    }
    @PutMapping
    public ResponseEntity<ProfesorOutputDTO> updateProfesor(@RequestBody ProfesorInputDTO profesorInputDTO){
        try{
            return ResponseEntity.ok().body(profesorService.updateProfesor(profesorInputDTO));
        }catch(EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProfesorById(@PathVariable Integer id){
        try{
            profesorService.deleteProfessorById(id);
            return ResponseEntity.ok().body("Se ha eliminado el profesor con el id " + id);
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }
    @PostMapping("/student")
    public ResponseEntity<String> addStudentProfesor(@RequestParam Integer idStudent, @RequestParam Integer idProfesor) {
        try {
            profesorService.addStudentToProfesor(idStudent, idProfesor);
            return ResponseEntity.ok().body("El estudiante con id " + idStudent +
                    "se agregó al profesor satisfactoriamente con id " + idProfesor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrió un fallo");
        }
    }

}
