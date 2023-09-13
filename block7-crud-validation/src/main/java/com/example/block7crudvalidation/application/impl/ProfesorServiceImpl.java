package com.example.block7crudvalidation.application.impl;

import com.example.block7crudvalidation.application.ProfesorService;
import com.example.block7crudvalidation.controller.dto.Profesor.ProfesorInputDTO;
import com.example.block7crudvalidation.controller.dto.Profesor.ProfesorOutputDTO;
import com.example.block7crudvalidation.entity.Asignatura;
import com.example.block7crudvalidation.entity.Persona;
import com.example.block7crudvalidation.entity.Profesor;
import com.example.block7crudvalidation.entity.Student;
import com.example.block7crudvalidation.error.EntityNotFoundException;
import com.example.block7crudvalidation.error.UnprocessableEntityException;
import com.example.block7crudvalidation.repository.PersonaRepository;
import com.example.block7crudvalidation.repository.ProfesorRepository;
import com.example.block7crudvalidation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    ProfesorRepository profesorRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PersonaRepository personaRepository;


    @Override
    public ProfesorOutputDTO addProfesor(ProfesorInputDTO profesorInputDTO) {
        if(profesorInputDTO.getId_persona() == 0){
            throw new UnprocessableEntityException("Inserte el Id de la persona asociada a este profesor");
        }
        else if(profesorInputDTO.getBranch() == null){
            throw new UnprocessableEntityException(("Inserte la rama a la que pertenece este profesor"));
        }

        //Creamos las instancias de Persona y profesor para establecer la relación entre ambas entidades
        Persona persona =personaRepository.findById(profesorInputDTO.getId_persona())
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado el id de la persona"));
        Profesor profesor = new Profesor(profesorInputDTO);

        if(persona.getStudent() != null){
            throw new EntityNotFoundException("La id de esta persona pertenece a un estudiante");
        }

        //Seteamos
        persona.setProfesor(profesor);
        profesor.setPersona(persona);

        return profesorRepository.save(profesor).profesorToOutPutDto();
    }




    @Override
    public ProfesorOutputDTO getProfesorById(Integer id) {
       return profesorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado el profesor con Id " + id)).profesorToOutPutDto();
    }

    @Override
    public Iterable<ProfesorOutputDTO> getAllProfesor(int pageNumber, int pageSize) {
        PageRequest Page = PageRequest.of(pageNumber, pageSize);
        return profesorRepository.findAll(Page).getContent().stream().map(Profesor::profesorToOutPutDto).toList();
    }

    @Override
    public ProfesorOutputDTO updateProfesor(ProfesorInputDTO profesorInputDTO) {
        Profesor profesor = profesorRepository.findById(profesorInputDTO.getId_profesor())
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado el profesor con el Id " + profesorInputDTO.getId_profesor()));

        profesor.setComments(profesorInputDTO.getComments());
        profesor.setBranch(profesor.getBranch());

        return profesorRepository.save(profesor).profesorToOutPutDto();
    }

    @Override
    public void addStudentToProfesor(Integer id_student, Integer id_profesor) {
        Student student = studentRepository.findById(id_student)
                .orElseThrow(() -> new EntityNotFoundException("No se encuentra el estudiante con el id " + id_student));
        Profesor profesor = profesorRepository.findById(id_profesor)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el profesor con el id " + id_profesor));

        student.setProfesor(profesor);
        profesor.getStudents().add(student);

        studentRepository.save(student);
        profesorRepository.save(profesor);
    }

    @Override
    public void deleteProfessorById(Integer id) {
        profesorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encuentra el profesor con el id " + id));
        profesorRepository.deleteById(id);
    }
}
