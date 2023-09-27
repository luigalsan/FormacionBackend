package com.bosonit.block13mongodb.application;

import com.bosonit.block13mongodb.entity.dto.PersonaInputDTO;
import com.bosonit.block13mongodb.entity.dto.PersonaOutputDTO;

import java.util.List;

public interface PersonaService {
    public PersonaOutputDTO addPersona(PersonaInputDTO persona);
    public Object getPersonaId(Long id, String param);
    public Object getPersonaByUsuario(String usuario, String param);
    public List<PersonaOutputDTO> getAllPersonas(int pageNumber, int pageSize);
    public PersonaOutputDTO updatePersona(PersonaInputDTO personaInputDTO);
    public void deletePersonaById(Long id);
}
