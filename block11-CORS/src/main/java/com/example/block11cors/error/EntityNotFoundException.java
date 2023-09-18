package com.example.block11cors.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    private CustomError customError;
    public EntityNotFoundException(String mensaje) {
        super(mensaje);
        customError = new CustomError(new Date(), 404, mensaje); //new Date() sin ponerle valor por parámetro devuelve la fecha de sistema
    }

    public CustomError getCustomError(){
        return customError;
    }
}
