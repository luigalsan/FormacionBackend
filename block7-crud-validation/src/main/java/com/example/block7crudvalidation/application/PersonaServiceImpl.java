package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.PersonaInputDTO;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDTO;
import com.example.block7crudvalidation.entity.Persona;
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
    public PersonaOutputDTO addPersona(PersonaInputDTO persona) throws Exception {

        //Comprobar que los campos insertados en persona están bien
        if(persona.getUsuario() == null){
            throw new Exception("El usuario no puede ser nulo");
        }
        else if(persona.getUsuario().length() > 10 || persona.getUsuario().length() < 6){
            throw new Exception("El usuario debe tener entre 10 y 6 caracteres");
        }
        else if(persona.getName() == null){
            throw new Exception("El nombre no puede ser nulo");
        }
        else if(persona.getPassword() == null){
            throw new Exception("La contraseña no puede ser nula");
        }
        else if(persona.getCompany_email() == null){
            throw new Exception("El email de la compañía no puede ser nulo");
        }
        else if(persona.getPersonal_email() == null){
            throw new Exception("El email personal no puede ser nulo");
        }
        else if(persona.getCity() == null){
            throw new Exception("El campo ciudad no puede ser nulo");
        }
//        else if(persona.getCreated_date() == null){
//            throw new Exception("La fecha no puede ser nula");
//        }

        //Comprobar si existe el usuario en la base de datos o no

       boolean existe = personaRepository.findAll().stream().anyMatch(p -> p.getUsuario().equals(persona.getUsuario()));
       if(!existe){
           return personaRepository.save(new Persona(persona)).personaToPersonaOutputDto();
       }
       throw new Exception("El usuario existe");
    }
    @Override
    public PersonaOutputDTO getPersonaById(int id) {
            return personaRepository.findById(id).orElseThrow().personaToPersonaOutputDto();
    }
    @Override
    public PersonaOutputDTO getPersonaByUsuario(String usuario) {
       return personaRepository.findAll().stream()
               .filter(persona -> persona.getUsuario()
                       .equals(usuario)).findFirst()
               .orElseThrow().personaToPersonaOutputDto();
    }

    @Override
    public List<PersonaOutputDTO> getAllStudents(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personaToPersonaOutputDto).toList();
    }


}
