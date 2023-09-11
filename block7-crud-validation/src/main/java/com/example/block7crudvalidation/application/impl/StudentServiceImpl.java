package com.example.block7crudvalidation.application.impl;

import com.example.block7crudvalidation.application.StudentService;
import com.example.block7crudvalidation.controller.dto.Asignatura.AsignaturaInputDTO;
import com.example.block7crudvalidation.controller.dto.Asignatura.AsignaturaOutputDTO;
import com.example.block7crudvalidation.controller.dto.Student.StudentOutputDtoFull;
import com.example.block7crudvalidation.controller.dto.Student.StudentOutputDtoSimple;
import com.example.block7crudvalidation.entity.Asignatura;
import com.example.block7crudvalidation.entity.Persona;
import com.example.block7crudvalidation.entity.Student;
import com.example.block7crudvalidation.error.EntityNotFoundException;
import com.example.block7crudvalidation.error.UnprocessableEntityException;
import com.example.block7crudvalidation.repository.PersonaRepository;
import com.example.block7crudvalidation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public StudentOutputDtoFull addStudent(StudentOutputDtoSimple studentInputDTO) {


        //Manejar excepciones en número de horas y branch
        if (studentInputDTO.getNum_hours_week() == 0) {
            throw new EntityNotFoundException("El número de horas no puede ser 0");
        } else if (studentInputDTO.getBranch() == null) {
            throw new EntityNotFoundException("La rama no puede estar vacía");
        }
            //Creación de variable con instancia de Persona y Student para luego setearlos y tener la relación 1:1
            Persona persona = personaRepository.findById(studentInputDTO.getId_persona()).orElseThrow(() -> new EntityNotFoundException("No se encontro la persona con ID "));
            Student student = new Student(studentInputDTO);

            //Verificar que el id de la persona introducida no pertenece a un profesor
            if(persona.getProfesor() != null){
                throw new EntityNotFoundException("La id de esta persona pertenece a un profesor");
            }
            //Seteo
            persona.setStudent(student);
            student.setPersona(persona);

            return studentRepository.save(student).toStudentOutputDtoFull();
        }

    @Override
    public StudentOutputDtoSimple getStudentByIdSimple(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontró el estudiante con el id: " + id)).toStudentOutputDtoSimple();
    }

    @Override
    public StudentOutputDtoFull getStudentByIdFull(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontró el estudiante con el id: " + id)).toStudentOutputDtoFull();
    }

    @Override
    public List<AsignaturaOutputDTO> getAsignaturasByIdStudent(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow( () -> new UnprocessableEntityException("No se ha encontrado el id del estudiante"));

        return student.getAsignaturas().stream().map(Asignatura::asignaturaToOutputDto).toList();
    }

    @Override
    public List<StudentOutputDtoSimple> getAllStudents(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return studentRepository.findAll(pageRequest).getContent().stream()
                .map(Student::toStudentOutputDtoSimple).toList();
    }

    @Override
    public StudentOutputDtoSimple updateStudent(StudentOutputDtoSimple studentInputDTO) {

        //Creo objeto Student para cambiar los atributos mediante el parámetro studentInputDTO
        //Algunos atributos como id_persona, id_profesor no se alterarán.
        Student student = studentRepository.findById(studentInputDTO.getId_student())
                .orElseThrow( () -> new EntityNotFoundException("No se encontró el estudiante con Id" + studentInputDTO.getId_student()));

        student.setId_student(studentInputDTO.getId_student());
        student.setNum_hours_week(studentInputDTO.getNum_hours_week());
        student.setComments(studentInputDTO.getComments());
        student.setBranch(studentInputDTO.getBranch());

        return student.toStudentOutputDtoSimple();

    }

    @Override
    public void deleteStudentById(Integer id) {

        int idUsuarioEliminado = studentRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("No se encontró el id: " + id + "para poder eliminar el elemento")).getId_student();
        studentRepository.deleteById(idUsuarioEliminado);

    }

}





















