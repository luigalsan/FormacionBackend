package com.example.block7crud.service;

import com.example.block7crud.rest.dto.PersonaInputDTO;
import com.example.block7crud.rest.dto.PersonaOutputDTO;

import java.util.List;

public interface PersonaService {

    PersonaOutputDTO addPersona(PersonaInputDTO persona);
    PersonaOutputDTO getPersonaById(int id);
    void deletePersonaById(int id);
    Iterable<PersonaOutputDTO> getAllPersonas(int pageNumber, int pageSize);
    PersonaOutputDTO updatePersona(PersonaInputDTO persona);

    Iterable<PersonaOutputDTO> getPersonaByName(String name, int pageNumber, int pageSize);

}
