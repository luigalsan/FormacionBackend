package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.Persona.PersonaInputDTO;
import com.example.block7crudvalidation.controller.dto.Persona.PersonaOutputDTO;
import com.example.block7crudvalidation.controller.dto.Persona.PersonaProfesorOutputDto;
import com.example.block7crudvalidation.controller.dto.Persona.PersonaStudentOutputDto;
import com.example.block7crudvalidation.entity.Persona;

public interface PersonaService {
    PersonaOutputDTO addPersona(PersonaInputDTO persona) throws Exception;
    Object getPersonaId(Integer id, String param);
    Object getPersonaByUsuario(String usuario, String param);
    Iterable<PersonaOutputDTO> getAllPersonas(int pageNumber, int pageSize);
    PersonaOutputDTO updatePersona(PersonaInputDTO persona);
    void deletePersonaById(Integer id);
}
