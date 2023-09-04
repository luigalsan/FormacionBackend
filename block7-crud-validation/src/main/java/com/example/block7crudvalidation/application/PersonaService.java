package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.PersonaInputDTO;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDTO;

public interface PersonaService {
    PersonaOutputDTO addPersona(PersonaInputDTO persona) throws Exception;
    PersonaOutputDTO getPersonaById(int id);
    PersonaOutputDTO getPersonaByUsuario(String usuario);
    Iterable<PersonaOutputDTO> getAllStudents(int pageNumber, int pageSize);

}
