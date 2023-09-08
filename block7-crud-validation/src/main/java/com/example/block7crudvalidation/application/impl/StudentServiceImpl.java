package com.example.block7crudvalidation.application.impl;

import com.example.block7crudvalidation.application.StudentService;
import com.example.block7crudvalidation.controller.dto.Student.StudentInputDTO;
import com.example.block7crudvalidation.controller.dto.Student.StudentOutputDtoFull;
import com.example.block7crudvalidation.controller.dto.Student.StudentOutputDtoSimple;
import com.example.block7crudvalidation.entity.Persona;
import com.example.block7crudvalidation.entity.Student;
import com.example.block7crudvalidation.error.EntityNotFoundException;
import com.example.block7crudvalidation.repository.PersonaRepository;
import com.example.block7crudvalidation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public StudentOutputDtoFull addStudent(StudentInputDTO studentInputDTO) {
        //Manejar excepciones en número de horas y branch
        if (studentInputDTO.getNum_hours_week() == 0) {
            throw new EntityNotFoundException("El número de horas no puede ser 0");
        } else if (studentInputDTO.getBranch() == null) {
            throw new EntityNotFoundException("La rama no puede estar vacía");
        }
        //Manejar excepción si estudiante ya existe
//        if (studentRepository.existsById(studentInputDTO.getId_persona())) {
//            throw new EntityNotFoundException("El estudiante con id: " + studentInputDTO.getId_persona() + "existe");
//        } else {

            //Creación de variable con instancia de Persona y Student para luego setearlos y tener la relación 1:1
            Persona persona = personaRepository.findById(studentInputDTO.getId_persona()).orElseThrow(() -> new EntityNotFoundException("No se encontro la persona con ID "));
            Student student = new Student(studentInputDTO);

            //Seteo
            persona.setStudent(student);
            student.setPersona(persona);

            return studentRepository.save(student).toStudentOutputDtoFull();
        }
    //    }
    //Getter por Id simple y full
    @Override
    public StudentOutputDtoSimple getStudentByIdSimple(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontró el estudiante con el id: " + id)).toStudentOutputDtoSimple();
    }

    @Override
    public StudentOutputDtoFull getStudentByIdFull(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontró el estudiante con el id: " + id)).toStudentOutputDtoFull();
    }

    @Override
    public StudentOutputDtoSimple updateStudent(StudentInputDTO studentInputDTO) {
        //Comprobar si el estudiante existe
        studentRepository.findById(studentInputDTO.getId_student())
                .orElseThrow();
       return studentRepository.save(new Student(studentInputDTO)).toStudentOutputDtoSimple();
    }

    @Override
    public void deleteStudentById(Integer id) {

        int idUsuarioEliminado = studentRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("No se encontró el id: " + id + "para poder eliminar el elemento")).getId_student();
        studentRepository.deleteById(idUsuarioEliminado);

    }

}





















