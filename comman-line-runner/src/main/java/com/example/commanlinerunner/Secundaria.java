package com.example.commanlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class Secundaria {


    @Bean
    public CommandLineRunner segundaFuncion(){
        return p -> {
            System.out.println("Hola desde la clase secundaria");
        };
    }
}
