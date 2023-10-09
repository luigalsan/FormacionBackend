package com.bosonit.blockcrudvalidation.controller;

import com.bosonit.blockcrudvalidation.application.impl.StudentServiceImpl;
import com.bosonit.blockcrudvalidation.controller.dto.Asignatura.AsignaturaOutputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Student.StudentInputDto;
import com.bosonit.blockcrudvalidation.controller.dto.Student.StudentOutputDtoFull;
import com.bosonit.blockcrudvalidation.controller.dto.Student.StudentOutputDtoSimple;
import com.bosonit.blockcrudvalidation.error.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentServiceImpl studentServiceImpl;

    @PostMapping
    public ResponseEntity<StudentOutputDtoFull> addStudent(@RequestBody StudentInputDto studentInputDTO){
        try{
            return ResponseEntity.ok().body(studentServiceImpl.addStudent(studentInputDTO));
        }catch(EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable Integer id, @RequestParam(value = "outputType", defaultValue = "simple") String ouputType){
        if(ouputType.equals("simple")){
            try{
                return ResponseEntity.ok(studentServiceImpl.getStudentByIdSimple(id));
            }catch(EntityNotFoundException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
            }
        }else{
            try{
                return ResponseEntity.ok(studentServiceImpl.getStudentByIdFull(id));
            }catch(EntityNotFoundException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
            }
        }
    }

    @GetMapping("/estudiante_asignatura/{id}")
    public Iterable<AsignaturaOutputDTO> getAsignaturasByIdStudent(@PathVariable Integer id){
            return studentServiceImpl.getAsignaturasByIdStudent(id);

    }

    @PutMapping
    public ResponseEntity<StudentOutputDtoSimple> updateStudent(@RequestBody StudentInputDto studentInputDTO) {
        try {
            studentServiceImpl.getStudentByIdSimple(studentInputDTO.getIdStudent()); //Obtengo el Id del objeto persona en POJO previamente serializado desde un JSON
            return ResponseEntity.ok().body(studentServiceImpl.updateStudent(studentInputDTO));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteStudentById(Integer id){
        try {
            studentServiceImpl.deleteStudentById(id);
            return ResponseEntity.ok().body("El estudiante con id " + id + " ha sido eliminada correctamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }

    @PostMapping("/asignarAsignatura/{idStudent}")
    public ResponseEntity<String> asignarAsignaturaToStudent(@PathVariable Integer idStudent, @RequestParam List<Integer> idAsignatura) {
        try {

            studentServiceImpl.asignarAsignaturasEstudiante(idStudent, idAsignatura);
            return ResponseEntity.ok().body("Se han agregado satisfactoriamente las asignaturas al estudiante con id: " + idStudent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrió un fallo");
        }
    }

    @PostMapping("/desasignarAsignatura/{idStudent}")
    public ResponseEntity<String> desasignarAsignaturaToStudent(@PathVariable Integer idStudent, @RequestParam List<Integer> idAsignatura) {
        try {

            studentServiceImpl.desasignarAsignaturasEstudiante(idStudent, idAsignatura);
            return ResponseEntity.ok().body("El estudiante con id " + idStudent +
                    "se agregó satisfactoriamente a la asignatura con id " + idAsignatura);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrió un fallo");
        }
    }

    @PostMapping("/asignatura")
    public ResponseEntity<String> addAsignaturatoStudent(@RequestParam Integer idStudent, @RequestParam Integer idAsignatura) {
        try {
            studentServiceImpl.addAsignaturaToStudent(idStudent, idAsignatura);
            return ResponseEntity.ok().body("El estudiante con id " + idStudent +
                    " se agregó satisfactoriamente a la asignatura con id " + idAsignatura);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrió un fallo");
        }
    }

}
