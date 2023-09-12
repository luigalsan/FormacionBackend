package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.impl.ProfesorServiceImpl;
import com.example.block7crudvalidation.application.impl.StudentServiceImpl;
import com.example.block7crudvalidation.controller.dto.Profesor.ProfesorInputDTO;
import com.example.block7crudvalidation.controller.dto.Profesor.ProfesorOutputDTO;
import com.example.block7crudvalidation.error.EntityNotFoundException;
import com.example.block7crudvalidation.error.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {

    @Autowired
    ProfesorServiceImpl profesorService;

    @Autowired
    StudentServiceImpl studentService;

    //
    @PostMapping
    public ResponseEntity<?> addProfesor(@RequestBody ProfesorInputDTO profesorInputDTO){
        try{
            profesorService.addProfesor(profesorInputDTO);
            return ResponseEntity.ok().body(profesorInputDTO);
        }catch(UnprocessableEntityException u){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(u.getCustomError());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProfesorById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok().body(profesorService.getProfesorById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }
    @GetMapping
    public Iterable<ProfesorOutputDTO> getAllProfesores(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {

        return profesorService.getAllProfesor(pageNumber, pageSize);
    }
    @PutMapping
    public ResponseEntity<?> updateProfesor(@RequestBody ProfesorInputDTO profesorInputDTO){
        try{
            return ResponseEntity.ok().body(profesorService.updateProfesor(profesorInputDTO));
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfesorById(@PathVariable Integer id){
        try{
            profesorService.deleteProfessorById(id);
            return ResponseEntity.ok().body("Se ha eliminado el profesor con el id " + id);
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }
    @PostMapping("/student")
    public ResponseEntity<String> addStudentProfesor(@RequestParam Integer id_student, @RequestParam Integer id_profesor) {
        try {
            profesorService.addStudentToProfesor(id_student, id_profesor);
            return ResponseEntity.ok().body("El estudiante con id " + id_student +
                    "se agregó al profesor satisfactoriamente con id " + id_profesor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrió un fallo");
        }
    }

}
