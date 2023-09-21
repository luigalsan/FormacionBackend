package com.bosonit.block7crud.rest.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaInputDTO {

    private int id;
    private String nombre;
    private String edad;
    private String poblacion;

}
