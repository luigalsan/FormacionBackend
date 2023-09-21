package com.bosonit.block7crud.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaOutputDTO {

    private int id;
    private String nombre;
    private String edad;
    private String poblacion;

}
