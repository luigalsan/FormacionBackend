package com.example.block6simplecontrollers.entity;

import org.springframework.stereotype.Component;


@Component
public class Persona {
    private String nombre;
    private String ciudad;
    private int edad;

    public Persona() {
    }

    public Persona(String nombre, int edad, String ciudad) {
        this.nombre = nombre;
        this.edad = edad;
        this.ciudad = ciudad;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPoblacion() {
        return ciudad;
    }

    public void setPoblacion(String poblacion) {
        this.ciudad = poblacion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
