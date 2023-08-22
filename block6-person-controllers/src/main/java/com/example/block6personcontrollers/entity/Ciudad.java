package com.example.block6personcontrollers.entity;

public class Ciudad {

    private String nombreCiudad;
    private int numeroHabitantes;

    public Ciudad(){

    }
    public Ciudad(String nombreCiudad, int numeroHabitantes) {
        this.nombreCiudad = nombreCiudad;
        this.numeroHabitantes = numeroHabitantes;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public int getNumeroHabitantes() {
        return numeroHabitantes;
    }

    public void setNumeroHabitantes(int numeroHabitantes) {
        this.numeroHabitantes = numeroHabitantes;
    }
}
