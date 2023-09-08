package com.example.block7crudvalidation.application.impl;

import com.example.block7crudvalidation.application.ProfesorService;
import com.example.block7crudvalidation.controller.dto.Profesor.ProfesorInputDTO;
import com.example.block7crudvalidation.controller.dto.Profesor.ProfesorOutputDTO;
import com.example.block7crudvalidation.entity.Profesor;
import com.example.block7crudvalidation.error.EntityNotFoundException;
import com.example.block7crudvalidation.repository.ProfesorRepository;
import com.example.block7crudvalidation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    ProfesorRepository profesorRepository;

    @Autowired
    StudentRepository studentRepository;


    @Override
    public ProfesorOutputDTO addProfesor(ProfesorInputDTO profesorInputDTO) {
        return profesorRepository.save(new Profesor(profesorInputDTO)).profesorToOutPutDto();
    }

    @Override
    public ProfesorOutputDTO getProfesorById(Integer id) {
       return profesorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado el profesor con Id" + id)).profesorToOutPutDto();
    }

    @Override
    public Iterable<ProfesorOutputDTO> getAllProfesor(int pageNumber, int pageSize) {
        PageRequest Page = PageRequest.of(pageNumber, pageSize);
        return profesorRepository.findAll(Page).getContent().stream().map(Profesor::profesorToOutPutDto).toList();
    }
//    @Override
//    public ProfesorOutputDTO updateProfesor(ProfesorInputDTO profesorInputDTO) {
//        profesorRepository.findById(profesorInputDTO.getId_profesor());
//        return profesorRepository.save(new Profesor(profesorInputDTO)).profesorToOutPutDto();
//    }

    @Override
    public void deleteProfessorById(Integer id) {

    }
}
