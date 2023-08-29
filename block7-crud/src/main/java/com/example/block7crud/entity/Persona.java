package com.example.block7crud.entity;

import com.example.block7crud.rest.dto.PersonaInputDTO;
import com.example.block7crud.rest.dto.PersonaOutputDTO;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PERSONA")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String edad;
    private String poblacion;


    //Al usar DTOs para enviar y recibir datos, se crean los siguientes métodos

    public Persona(PersonaInputDTO personaInputDTO){
        this.id = personaInputDTO.getId();
        this.nombre = personaInputDTO.getNombre();
        this.edad = personaInputDTO.getEdad();
        this.poblacion = personaInputDTO.getPoblacion();
    }

    public PersonaOutputDTO personaToPersonaOutputDto(){
        return new PersonaOutputDTO(
                this.id,
                this.edad,
                this.nombre,
                this.poblacion
        );
    }



}
