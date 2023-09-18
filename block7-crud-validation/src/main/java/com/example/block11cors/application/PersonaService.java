package com.example.block11cors.application;

import com.example.block11cors.controller.dto.Persona.PersonaInputDTO;
import com.example.block11cors.controller.dto.Persona.PersonaOutputDTO;

public interface PersonaService {
    PersonaOutputDTO addPersona(PersonaInputDTO persona) throws Exception;
    Object getPersonaId(Integer id, String param);
    Object getPersonaByUsuario(String usuario, String param);
    Iterable<PersonaOutputDTO> getAllPersonas(int pageNumber, int pageSize);
    PersonaOutputDTO updatePersona(PersonaInputDTO persona);
    void deletePersonaById(Integer id);
}
