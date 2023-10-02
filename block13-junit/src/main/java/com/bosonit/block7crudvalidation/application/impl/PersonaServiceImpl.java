package com.bosonit.block7crudvalidation.application.impl;

import com.bosonit.block7crudvalidation.controller.dto.Persona.PersonaInputDTO;
import com.bosonit.block7crudvalidation.controller.dto.Persona.PersonaOutputDTO;
import com.bosonit.block7crudvalidation.error.EntityNotFoundException;
import com.bosonit.block7crudvalidation.error.UnprocessableEntityException;
import com.bosonit.block7crudvalidation.application.PersonaService;
import com.bosonit.block7crudvalidation.entity.Persona;
import com.bosonit.block7crudvalidation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;
    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO persona) {
        //Comprobar que los campos insertados en persona están bien
        if(persona.getUsuario() == null || persona.getUsuario().isEmpty())
            throw new UnprocessableEntityException("Inserte usuario, por favor");

        else if(persona.getUsuario().length() > 10 || persona.getUsuario().length() < 6)
            throw new UnprocessableEntityException("El usuario debe tener entre 10 y 6 caracteres");

       boolean existe = personaRepository.findAll().stream().anyMatch(p -> p.getUsuario().equals(persona.getUsuario()));
       if(!existe){
           return personaRepository.save(new Persona(persona)).personaToPersonaOutputDto();
       }
       throw new UnprocessableEntityException("El usuario existe");
    }


    @Override
    public Object getPersonaId(Integer id, String param) {

        switch(param){
            case "alumno":
                return personaRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la persona con el id " + id)).personaToPersonaStudentDto();
            case "profesor":
                return personaRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la persona con el id " + id)).personaToPersonaProfesorDto();
            default:
                return personaRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la persona con el id "+ id)).personaToPersonaOutputDto();
        }
    }

    @Override
    public Object getPersonaByUsuario(String usuario, String param) {

        switch(param){
            case "alumno":
                return personaRepository.findAll().stream()
                        .filter(persona -> persona.getUsuario()
                                .equals(usuario)).findFirst()
                        .orElseThrow(() -> new EntityNotFoundException("No se encontró el usuario: " + usuario))
                        .personaToPersonaStudentDto();
            case "profesor":
                return personaRepository.findAll().stream()
                        .filter(persona -> persona.getUsuario()
                                .equals(usuario)).findFirst()
                        .orElseThrow(() -> new EntityNotFoundException("No se encontró el usuario: " + usuario))
                        .personaToPersonaProfesorDto();
            default:
                return personaRepository.findAll().stream()
                        .filter(persona -> persona.getUsuario()
                                .equals(usuario)).findFirst()
                        .orElseThrow(() -> new EntityNotFoundException("No se encontró el usuario: " + usuario))
                        .personaToPersonaOutputDto();
        }
    }

    @Override
    public List<PersonaOutputDTO> getAllPersonas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personaToPersonaOutputDto).toList();
    }


    @Override
    public PersonaOutputDTO updatePersona(PersonaInputDTO personaInputDTO) {

        Persona persona = personaRepository.findById(personaInputDTO.getId_persona())
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la persona con el Id " + personaInputDTO.getId_persona()));

        persona.setName(personaInputDTO.getName());
        persona.setSurname(personaInputDTO.getSurname());
        persona.setUsuario(personaInputDTO.getUsuario());
        persona.setPassword(personaInputDTO.getPassword());
        persona.setCompany_email(personaInputDTO.getCompany_email());
        persona.setPersonal_email(personaInputDTO.getPersonal_email());
        persona.setCity(personaInputDTO.getCity());
        persona.setActive(personaInputDTO.isActive());
        persona.setCreated_date(personaInputDTO.getCreated_date());
        persona.setImagen_url(personaInputDTO.getImagen_url());
        persona.setTermination_date(personaInputDTO.getTermination_date());

        return personaRepository.save(persona).personaToPersonaOutputDto();
    }

    @Override
    public void deletePersonaById(Integer id) {

        int idUsuarioEliminado = personaRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("No se encontró el id: " + id + " para poder eliminar el elemento")).getId_persona();
        personaRepository.deleteById(idUsuarioEliminado);
    }

}
