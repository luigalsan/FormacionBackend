package com.bosonit.profilesInterfaces;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("INIT")
public class DevolverMensaje implements Mensaje{


    @Override
    public String getMessage() {
        return "Esto es un mensaje desde el perfil INIT";
    }
}

@Component
@Profile("PRO")
class DevolverMensaje2 implements Mensaje{


    @Override
    public String getMessage() {
        return "Esto es un mensaje desde el perfil PRO";
    }
}


