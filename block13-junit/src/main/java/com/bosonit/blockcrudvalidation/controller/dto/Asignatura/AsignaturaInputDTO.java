package com.bosonit.blockcrudvalidation.controller.dto.Asignatura;


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

    private Integer IdAsignatura;
    private String asignatura;
    private String comment;
    private Date initialDate;
    private Date finishDate;

}
