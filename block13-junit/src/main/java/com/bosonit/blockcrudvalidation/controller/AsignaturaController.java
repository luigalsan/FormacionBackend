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
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/asignatura")
public class AsignaturaController {

    @Autowired
    AsignaturaServiceImpl asignaturaService;

    @PostMapping
    @ResponseStatus
    public ResponseEntity<AsignaturaOutputDTO> addAsignatura(@RequestBody AsignaturaInputDTO asignaturaInputDTO) {
        try {
            return ResponseEntity.ok().body(asignaturaService.addAsignatura(asignaturaInputDTO));

        } catch (UnprocessableEntityException u) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, u.getMessage());
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus
    public ResponseEntity<AsignaturaOutputDTO> getAsignaturaById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok().body(asignaturaService.getAsignaturaById(id));


        }catch(EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());        }
    }
    @GetMapping
    public Iterable<AsignaturaOutputDTO> getAllAsignaturas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {

        return asignaturaService.getAllAsignaturas(pageNumber, pageSize);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAsignaturaById(@PathVariable Integer id){
        try{
            asignaturaService.deleteAsignaturaById(id);
            return ResponseEntity.ok().body("La asignatura con id " + id + "ha sido eliminado");

        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }

    @PutMapping
    public ResponseEntity<AsignaturaOutputDTO> updateAsignatura(@RequestBody AsignaturaInputDTO asignaturaInputDTO){
        try{
            return ResponseEntity.ok().body(asignaturaService.updateAsignatura(asignaturaInputDTO));

        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}













