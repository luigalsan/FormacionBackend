package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.PersonaInputDTO;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDTO;
import com.example.block7crudvalidation.entity.Persona;
import com.example.block7crudvalidation.error.EntityNotFoundException;
import com.example.block7crudvalidation.error.UnprocessableEntityException;
import com.example.block7crudvalidation.repository.PersonaRepository;
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
            throw new UnprocessableEntityException("El usuario no puede estar vacío");

        else if(persona.getUsuario().length() > 10 || persona.getUsuario().length() < 6)
            throw new UnprocessableEntityException("El usuario debe tener entre 10 y 6 caracteres");

        else if(persona.getName() == null || persona.getName().isEmpty())
            throw new UnprocessableEntityException("El nombre no puede ser nulo");

        else if(persona.getPassword() == null || persona.getPassword().isEmpty())
            throw new UnprocessableEntityException("Inserte contraseña");

        else if(persona.getCompany_email() == null || persona.getCompany_email().isEmpty())
            throw new UnprocessableEntityException("El email de la compañía no puede ser nulo");

        else if(persona.getPersonal_email() == null || persona.getPersonal_email().isEmpty())
            throw new UnprocessableEntityException("El email personal no puede ser nulo");

        else if(persona.getCity() == null || persona.getCity().isEmpty())
            throw new UnprocessableEntityException("El campo ciudad no puede ser nulo");

        else if(persona.getCreated_date() == null){ //Al ser un objeto de tipo Date no puede hacer uso del método isEmpty()
            throw new UnprocessableEntityException("La fecha no puede ser nula");
        }

        //Comprobar si existe el usuario en la base de datos o no

       boolean existe = personaRepository.findAll().stream().anyMatch(p -> p.getUsuario().equals(persona.getUsuario()));
       if(!existe){
           return personaRepository.save(new Persona(persona)).personaToPersonaOutputDto();
       }
       throw new UnprocessableEntityException("El usuario existe");
    }
    @Override
    public PersonaOutputDTO getPersonaById(int id) {
            return personaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro el registro con ID: " + id))
                    .personaToPersonaOutputDto();
    }

    @Override
    public PersonaOutputDTO getPersonaByUsuario(String usuario) {
       return personaRepository.findAll().stream()
               .filter(persona -> persona.getUsuario()
                       .equals(usuario)).findFirst()
               .orElseThrow(() -> new EntityNotFoundException("No se encontró el usuario: " + usuario))
               .personaToPersonaOutputDto();
    }
    @Override
    public List<PersonaOutputDTO> getAllStudents(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personaToPersonaOutputDto).toList();
    }
    @Override
    public PersonaOutputDTO updatePersona(PersonaInputDTO persona) {
        personaRepository.findById(persona.getId()).orElseThrow();

        return personaRepository.save(new Persona(persona))
                .personaToPersonaOutputDto();
    }
    @Override
    public void deletePersonaById(int id) {

        int idUsuarioEliminado = personaRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("No se encontró el id: " + id + "para poder eliminar el elemento")).getId();
        personaRepository.deleteById(idUsuarioEliminado);
    }


}