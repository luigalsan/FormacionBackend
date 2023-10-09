package com.bosonit.blockcrudvalidation.application.impl;

import com.bosonit.blockcrudvalidation.controller.dto.Profesor.ProfesorInputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Profesor.ProfesorOutputDTO;
import com.bosonit.blockcrudvalidation.error.EntityNotFoundException;
import com.bosonit.blockcrudvalidation.error.UnprocessableEntityException;
import com.bosonit.blockcrudvalidation.application.ProfesorService;
import com.bosonit.blockcrudvalidation.entity.Persona;
import com.bosonit.blockcrudvalidation.entity.Profesor;
import com.bosonit.blockcrudvalidation.entity.Student;
import com.bosonit.blockcrudvalidation.repository.PersonaRepository;
import com.bosonit.blockcrudvalidation.repository.ProfesorRepository;
import com.bosonit.blockcrudvalidation.repository.StudentRepository;
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
        if(profesorInputDTO.getIdPersona() == 0){
            throw new UnprocessableEntityException("Inserte el Id de la persona asociada a este profesor");
        }
        else if(profesorInputDTO.getBranch() == null){
            throw new UnprocessableEntityException(("Inserte la rama a la que pertenece este profesor"));
        }

        //Creamos las instancias de Persona y profesor para establecer la relación entre ambas entidades
        Persona persona =personaRepository.findById(profesorInputDTO.getIdPersona())
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
        PageRequest page = PageRequest.of(pageNumber, pageSize);
        return profesorRepository.findAll(page).getContent().stream().map(Profesor::profesorToOutPutDto).toList();
    }

    @Override
    public ProfesorOutputDTO updateProfesor(ProfesorInputDTO profesorInputDTO) {
        Profesor profesor = profesorRepository.findById(profesorInputDTO.getIdProfesor())
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado el profesor con el Id " + profesorInputDTO.getIdProfesor()));

        profesor.setComments(profesorInputDTO.getComments());
        profesor.setBranch(profesor.getBranch());

        return profesorRepository.save(profesor).profesorToOutPutDto();
    }

    @Override
    public void addStudentToProfesor(Integer idStudent, Integer idProfesor) {
        Student student = studentRepository.findById(idStudent)
                .orElseThrow(() -> new EntityNotFoundException("No se encuentra el estudiante con el id " + idStudent));
        Profesor profesor = profesorRepository.findById(idProfesor)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el profesor con el id " + idProfesor));

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
