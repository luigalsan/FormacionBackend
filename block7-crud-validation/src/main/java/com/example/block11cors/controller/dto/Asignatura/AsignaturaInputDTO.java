package com.example.block11cors.controller.dto.Asignatura;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaInputDTO {

    private Integer id_asignatura;
    private String asignatura;
    private String comment;
    private Date initial_date;
    private Date finish_date;

}
