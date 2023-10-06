package com.bosonit.blockcrudvalidation.controller.dto.Profesor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorInputDTO {

    private Integer idProfesor;
    private String comments;
    private String branch;
    private Integer idPersona;


}
