package com.bosonit.block7crudvalidation.controller;

import com.bosonit.block7crudvalidation.application.impl.PersonaServiceImpl;
import com.bosonit.block7crudvalidation.controller.PersonaController;
import com.bosonit.block7crudvalidation.controller.dto.Persona.PersonaOutputDTO;
import com.bosonit.block7crudvalidation.error.CustomError;
import com.bosonit.block7crudvalidation.error.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PersonaControllerTest {


    //Igual que en caso del servicio de Persona, se crea un mock del servicio en este caso para inyectarlo en el controller y hacer pruebas
    @InjectMocks
    private PersonaController personaController;
    @Mock
    private PersonaServiceImpl personaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**************************************** TESTEANDO testFindPersonById_Success **************************************************/

    @Test
    public void testFindPersonById_Success() {
        int id = 1; // Supongamos un ID válido
        String outputType = "default";

        // Configura el comportamiento del servicio para que devuelva un PersonaOutputDTO
        PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO();
        when(personaService.getPersonaId(id, outputType)).thenReturn(personaOutputDTO);

        // Ejecuta el método del controlador
        ResponseEntity<?> response = personaController.findPersonById(id, outputType);

        // Verifica que se devuelva una respuesta exitosa (200 OK)
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Verifica que el cuerpo de la respuesta sea el PersonaOutputDTO esperado
        assertEquals(personaOutputDTO, response.getBody());
    }

    @Test
    public void testFindPersonById_EntityNotFoundException() {
        int id = 1; // Supongamos un ID válido
        String outputType = "default";

        // Configura el comportamiento del servicio para que lance una EntityNotFoundException
        when(personaService.getPersonaId(id, outputType)).thenThrow(new EntityNotFoundException("No se encontró el usuario con ID: " + id));

        // Ejecuta el método del controlador
        ResponseEntity<?> response = personaController.findPersonById(id, outputType);

        // Verifica que se devuelva un código de estado 404 (NOT FOUND)
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        // Verifica el mensaje en el objeto CustomError
        CustomError customError = (CustomError) response.getBody();
        assertEquals("No se encontró el usuario con ID: " + id, customError.getMensaje());
    }



}
