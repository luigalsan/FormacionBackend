package com.example.block6personcontrollers.rest;


import com.example.block6personcontrollers.config.PersonaConfig;
import com.example.block6personcontrollers.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorBean {

    //Inyectar dependencias de cada Bean

    @Autowired
    @Qualifier("bean1")
    private Persona personaBean1;

    @Autowired
    @Qualifier("bean2")
    private Persona personaBean2;

    @Autowired
    @Qualifier("bean3")
    private Persona personaBean3;

    @GetMapping("/controlador/bean/{bean}")
    public Persona getPersonaBean(@PathVariable String bean){
        switch(bean){
            case "bean1":
                return personaBean1;
            case "bean2":
                return personaBean2;
            case "bean3":
                return personaBean3;
        }
        return null;
    }
}
