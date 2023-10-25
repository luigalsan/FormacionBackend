package com.bosonit.springcloudbackendFrontend.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Cliente {

    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private String telefono;
}