package com.bosonit.cloud.backend.springcloudbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteOutputDto {

    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private int telefono;
}