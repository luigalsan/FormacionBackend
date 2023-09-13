package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.impl.StudentServiceImpl;
import com.example.block7crudvalidation.controller.dto.Asignatura.AsignaturaOutputDTO;
import com.example.block7crudvalidation.controller.dto.Student.StudentInputDto;
import com.example.block7crudvalidation.controller.dto.Student.StudentOutputDtoSimple;
import com.example.block7crudvalidation.entity.Student;
import com.example.block7crudvalidation.error.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentServiceImpl studentServiceImpl;

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody StudentInputDto studentInputDTO){
        try{
            return ResponseEntity.ok().body(studentServiceImpl.addStudent(studentInputDTO));
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Integer id, @RequestParam(value = "outputType", defaultValue = "simple") String ouputType){
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
    public ResponseEntity<?> updatePersona(@RequestBody StudentInputDto studentInputDTO) {
        try {
            studentServiceImpl.getStudentByIdSimple(studentInputDTO.getId_student()); //Obtengo el Id del objeto persona en POJO previamente serializado desde un JSON
            return ResponseEntity.ok().body(studentServiceImpl.updateStudent(studentInputDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStudentById(Integer id){
        try {
            studentServiceImpl.deleteStudentById(id);
            return ResponseEntity.ok().body("El estudiante con id " + id + " ha sido eliminada correctamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
    }

    @PostMapping("/asignarAsignatura/{id_student}")
    public ResponseEntity<String> asignarAsignaturaToStudent(@PathVariable Integer id_student, @RequestParam List<Integer> id_asignatura) {
        try {

            studentServiceImpl.asignarAsignaturasEstudiante(id_student, id_asignatura);
            return ResponseEntity.ok().body("Se han agregado satisfactoriamente las asignaturas al estudiante con id: " + id_student);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrió un fallo");
        }
    }

    @PostMapping("/desasignarAsignatura/{id_student}")
    public ResponseEntity<String> desasignarAsignaturaToStudent(@PathVariable Integer id_student, @RequestParam List<Integer> id_asignatura) {
        try {

            studentServiceImpl.desasignarAsignaturasEstudiante(id_student, id_asignatura);
            return ResponseEntity.ok().body("El estudiante con id " + id_student +
                    "se agregó satisfactoriamente a la asignatura con id " + id_asignatura);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrió un fallo");
        }
    }

    @PostMapping("/asignatura")
    public ResponseEntity<String> addAsignaturatoStudent(@RequestParam Integer id_student, @RequestParam Integer id_asignatura) {
        try {
            studentServiceImpl.addAsignaturaToStudent(id_student, id_asignatura);
            return ResponseEntity.ok().body("El estudiante con id " + id_student +
                    " se agregó satisfactoriamente a la asignatura con id " + id_asignatura);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrió un fallo");
        }
    }

}
