package com.bosonit.blockcrudvalidation.application;

import com.bosonit.blockcrudvalidation.controller.dto.Profesor.ProfesorInputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Profesor.ProfesorOutputDTO;

public interface ProfesorService {
    ProfesorOutputDTO addProfesor(ProfesorInputDTO personaInputDTO);
    ProfesorOutputDTO getProfesorById(Integer id);
    Iterable<ProfesorOutputDTO> getAllProfesor(int pageNumber, int pageSize);
    ProfesorOutputDTO updateProfesor(ProfesorInputDTO profesorInputDTO);
    void addStudentToProfesor(Integer id_profesor, Integer id_asignatura);
    void deleteProfessorById(Integer id);
}
