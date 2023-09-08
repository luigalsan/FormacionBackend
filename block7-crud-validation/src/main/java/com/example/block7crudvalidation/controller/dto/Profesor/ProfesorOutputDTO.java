package com.example.block7crudvalidation.controller.dto.Profesor;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorOutputDTO {

    private Integer id_profesor;
    private String comments;
    private String branch;
    private Integer id_persona;

}
