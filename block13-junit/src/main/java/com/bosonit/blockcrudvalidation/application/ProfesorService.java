package com.bosonit.blockcrudvalidation.application;

import com.bosonit.blockcrudvalidation.controller.dto.Profesor.ProfesorInputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Profesor.ProfesorOutputDTO;

public interface ProfesorService {
    ProfesorOutputDTO addProfesor(ProfesorInputDTO personaInputDTO);
    ProfesorOutputDTO getProfesorById(Integer id);
    Iterable<ProfesorOutputDTO> getAllProfesor(int pageNumber, int pageSize);
    ProfesorOutputDTO updateProfesor(ProfesorInputDTO profesorInputDTO);
    void addStudentToProfesor(Integer idProfesor, Integer idAsignatura);
    void deleteProfessorById(Integer id);
}
