package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.Persona.PersonaInputDTO;
import com.example.block7crudvalidation.controller.dto.Persona.PersonaOutputDTO;

public interface PersonaService {
    PersonaOutputDTO addPersona(PersonaInputDTO persona) throws Exception;
    PersonaOutputDTO getPersonaById(Integer id);
    PersonaOutputDTO getPersonaByUsuario(String usuario);
    Iterable<PersonaOutputDTO> getAllPersonas(int pageNumber, int pageSize);
    PersonaOutputDTO updatePersona(PersonaInputDTO persona);
    void deletePersonaById(Integer id);
}
