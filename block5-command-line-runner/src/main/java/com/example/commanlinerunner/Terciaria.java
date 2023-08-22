package com.example.commanlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Terciaria {

    public Terciaria(){

    }
    //LOS MENSAJES DE CommandLineRunner se ejecutarán al final
    @Bean
    public CommandLineRunner terceraFuncion(){
        return p -> {
            if (p.length > 0) {
                System.out.println("Hola desde la clase tercera");
                System.out.println("Valores pasados como parámetro:");
                for (String valores : p) {
                    System.out.println(valores);
                }
            } else {
                System.out.println("No se pasaron valores como parámetro.");
            }
        };
    }
}
