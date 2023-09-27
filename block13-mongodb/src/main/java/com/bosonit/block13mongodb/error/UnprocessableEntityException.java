package com.bosonit.block13mongodb.error;

import java.util.Date;

public class UnprocessableEntityException extends RuntimeException {
    private CustomError customError;
    public UnprocessableEntityException(String mensaje) {
        super(mensaje);
        customError = new CustomError(new Date(), 422, mensaje);
    }

    public CustomError getCustomError(){
        return customError;
    }
}