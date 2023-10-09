package com.bosonit.blockcrudvalidation.controller;

import com.bosonit.blockcrudvalidation.application.impl.AsignaturaServiceImpl;
import com.bosonit.blockcrudvalidation.controller.dto.Asignatura.AsignaturaInputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Asignatura.AsignaturaOutputDTO;
import com.bosonit.blockcrudvalidation.error.EntityNotFoundException;
import com.bosonit.blockcrudvalidation.error.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/asignatura")
public class AsignaturaController {

    @Autowired
    AsignaturaServiceImpl asignaturaService;

    @PostMapping
    public ResponseEntity<?> addAsignatura(@RequestBody AsignaturaInputDTO asignaturaInputDTO){
        try{
            asignaturaService.addAsignatura(asignaturaInputDTO);
            return ResponseEntity.ok().body(asignaturaInputDTO);
        }catch(UnprocessableEntityException u){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(u.getCustomError());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAsignaturaById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok().body(asignaturaService.getAsignaturaById(id));
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }
    @GetMapping
    public Iterable<AsignaturaOutputDTO> getAllAsignaturas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {

        return asignaturaService.getAllAsignaturas(pageNumber, pageSize);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAsignaturaById(@PathVariable Integer id){
        try{
            asignaturaService.deleteAsignaturaById(id);
            return ResponseEntity.ok().body("El estudiante con id " + id + "ha sido eliminado");

        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateAsignatura(@RequestBody AsignaturaInputDTO asignaturaInputDTO){
        try{
            return ResponseEntity.ok().body(asignaturaService.updateAsignatura(asignaturaInputDTO));

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }
}













