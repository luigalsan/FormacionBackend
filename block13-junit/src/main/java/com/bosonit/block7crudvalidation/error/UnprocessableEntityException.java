package com.bosonit.block7crudvalidation.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
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
