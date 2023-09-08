package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.Persona.PersonaInputDTO;
import com.example.block7crudvalidation.controller.dto.Profesor.ProfesorInputDTO;
import com.example.block7crudvalidation.controller.dto.Profesor.ProfesorOutputDTO;

public interface ProfesorService {
    ProfesorOutputDTO addProfesor(ProfesorInputDTO personaInputDTO);
    ProfesorOutputDTO getProfesorById(Integer id);
    Iterable<ProfesorOutputDTO> getAllProfesor(int pageNumber, int pageSize);
    ProfesorOutputDTO updateProfesor(ProfesorInputDTO profesorInputDTO);
    void deleteProfessorById(Integer id);
}
