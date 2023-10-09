package com.bosonit.blockcrudvalidation.application;

import com.bosonit.blockcrudvalidation.controller.dto.Persona.PersonaInputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Persona.PersonaOutputDTO;
import com.bosonit.blockcrudvalidation.error.UnprocessableEntityException;

public interface PersonaService {
    PersonaOutputDTO addPersona(PersonaInputDTO persona) throws UnprocessableEntityException;
    Object getPersonaId(Integer id, String param);
    Object getPersonaByUsuario(String usuario, String param);
    Iterable<PersonaOutputDTO> getAllPersonas(int pageNumber, int pageSize);
    PersonaOutputDTO updatePersona(PersonaInputDTO persona);
    void deletePersonaById(Integer id);
}
