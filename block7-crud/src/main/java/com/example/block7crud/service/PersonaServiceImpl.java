package com.example.block7crud.service;

import com.example.block7crud.entity.Persona;
import com.example.block7crud.repository.PersonaRepository;
import com.example.block7crud.rest.dto.PersonaInputDTO;
import com.example.block7crud.rest.dto.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonaServiceImpl implements PersonaService{
    
    @Autowired
    PersonaRepository personaRepository;
    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO persona) {
        return personaRepository.save(new Persona(persona)).personaToPersonaOutputDto();
    }
    public PersonaOutputDTO getPersonaById(int id){
        return personaRepository.findById(id).orElseThrow() //Al devolver findById() un objeto Optional, puedo usar orElseThrow() para manejar si no existe el id, este método por defecto devuelve un NoSuchElementException
                .personaToPersonaOutputDto();
    }
    @Override
    public void deletePersonaById(int id) {
        personaRepository.findById(id).orElseThrow(); //Esta línea para comprobar que existe el ID
        personaRepository.deleteById(id);
    }

//    @Override
//    public PersonaOutputDTO updatePersona(PersonaInputDTO persona) {
//        personaRepository.findById(persona.getId()).orElseThrow();
//
//        return personaRepository.save(new Persona(persona))
//                .personaToPersonaOutputDto();
//    }

    @Override
    public Iterable<PersonaOutputDTO> getPersonaByName(String nombre, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream().filter(persona -> persona.getNombre().equals(nombre))
                .map(Persona::personaToPersonaOutputDto).toList();
    }

    @Override
    public List<PersonaOutputDTO> getAllPersonas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personaToPersonaOutputDto).toList(); //en el map, cada objeto Persona del flujo lo va a convertir a PersonaOutputDto.
    }


}
