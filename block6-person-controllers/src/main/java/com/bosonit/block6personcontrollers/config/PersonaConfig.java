package com.bosonit.block6personcontrollers.config;


import com.bosonit.block6personcontrollers.entity.Persona;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonaConfig {

    @Bean(name = "bean1")
    public Persona crearPersona1(){

        return new Persona("Pepito", "Rota", 20);
    }

    @Bean(name = "bean2")
    public Persona crearPersona2(){

        return new Persona("Benganito", "Cádiz", 30);
    }

    @Bean(name = "bean3")
    public Persona crearPersona3(){

        return new Persona("Iván Campo", "Madrid", 40);
    }
}
