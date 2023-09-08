package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.impl.StudentServiceImpl;
import com.example.block7crudvalidation.controller.dto.Student.StudentInputDTO;
import com.example.block7crudvalidation.error.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentServiceImpl studentServiceImpl;

    @PostMapping
    public ResponseEntity<Object> addStudent(@RequestBody StudentInputDTO studentInputDTO){
        try{
            return ResponseEntity.ok().body(studentServiceImpl.addStudent(studentInputDTO));
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCustomError());
        }
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
}
