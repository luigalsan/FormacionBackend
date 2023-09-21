package com.bosonit.block7crud.service;

import com.bosonit.block7crud.rest.dto.PersonaInputDTO;
import com.bosonit.block7crud.rest.dto.PersonaOutputDTO;

public interface PersonaService {

    PersonaOutputDTO addPersona(PersonaInputDTO persona);
    PersonaOutputDTO getPersonaById(int id);
    void deletePersonaById(int id);
    Iterable<PersonaOutputDTO> getAllPersonas(int pageNumber, int pageSize);
    PersonaOutputDTO updatePersona(PersonaInputDTO persona);

    Iterable<PersonaOutputDTO> getPersonaByName(String name, int pageNumber, int pageSize);

}
