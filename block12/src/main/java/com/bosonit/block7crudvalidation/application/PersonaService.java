package com.bosonit.block7crudvalidation.application;

import com.bosonit.block7crudvalidation.controller.dto.Persona.PersonaInputDTO;
import com.bosonit.block7crudvalidation.controller.dto.Persona.PersonaOutputDTO;

import java.util.Date;

public interface PersonaService {
    PersonaOutputDTO addPersona(PersonaInputDTO persona) throws Exception;
    Object getPersonaId(Integer id, String param);
    Object getPersonaByUsuario(String usuario, String param);
    Iterable<PersonaOutputDTO> getAllPersonas(int pageNumber, int pageSize);
    PersonaOutputDTO updatePersona(PersonaInputDTO persona);
    void deletePersonaById(Integer id);

    Iterable<PersonaOutputDTO> filterPersonas(String user, String name, String surname, Date fechaCreacionMin, Date fechaCreacionMax, String orderBy);
}
