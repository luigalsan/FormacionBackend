package com.bosonit.block7crudvalidation.controller;

import com.bosonit.block7crudvalidation.application.impl.PersonaServiceImpl;
import com.bosonit.block7crudvalidation.controller.dto.Persona.PersonaInputDTO;
import com.bosonit.block7crudvalidation.controller.dto.Persona.PersonaOutputDTO;
import com.bosonit.block7crudvalidation.entity.Persona;
import com.bosonit.block7crudvalidation.error.CustomError;
import com.bosonit.block7crudvalidation.error.EntityNotFoundException;
import com.bosonit.block7crudvalidation.error.UnprocessableEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

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
        MockitoAnnotations.openMocks(this);
    }

    /**************************************** TESTEANDO getPersonById **************************************************/

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
        //Añadir que el servicio se ha llamado
        verify(personaService).getPersonaId(id, outputType);
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


    /**************************************** TESTEANDO getPersonByUsuario **************************************************/

    @Test
    public void testFindPersonByUsuario() {
        // Configurar el comportamiento esperado del servicio
        String usuario = "luis1991";
        String outputType = "default";
        PersonaOutputDTO personaOutputDto = new PersonaOutputDTO(); // Simula una persona encontrada
        when(personaService.getPersonaByUsuario(usuario, outputType)).thenReturn(personaOutputDto);

        // Llamar al método del controlador
        ResponseEntity<?> responseEntity = personaController.findPersonByUsuario(usuario, outputType);

        // Verificar que se devuelva HttpStatus.OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verificar que el contenido de la respuesta sea la persona simulada
        assertEquals(personaOutputDto, responseEntity.getBody());

        // Verificar que el servicio getPersonaByUsuario se haya llamado con los parámetros correctos
        verify(personaService).getPersonaByUsuario(usuario, outputType);
    }

    @Test
    public void testFindPersonByUsuarioNotFound(){
        // Configurar el comportamiento esperado del servicio para lanzar una EntityNotFoundException
        String usuario = "user_not_found";
        String outputType = "default";
        when(personaService.getPersonaByUsuario(usuario, outputType))
                .thenThrow(new EntityNotFoundException("No se encontró la persona"));

        // Llamar al método del controlador
        ResponseEntity<?> responseEntity = personaController.findPersonByUsuario(usuario, outputType);

        // Verificar que se devuelva HttpStatus.NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    /**************************************** TESTEANDO getPersonByUsuario **************************************************/

    @Test
    public void testGetAllPersonas() {
        // Configurar el comportamiento esperado del servicio
        int pageNumber = 0;
        int pageSize = 4;
        Iterable<PersonaOutputDTO> personasOutputDto = crearListaDePersonasOutputDto(); // Simula una lista de personas encontradas
        when(personaService.getAllPersonas(pageNumber, pageSize)).thenReturn((List<PersonaOutputDTO>) personasOutputDto); //Castear personas que es de tipo Iterable a List

        // Llamar al método del controlador
        Iterable<PersonaOutputDTO> result = personaController.getAllPersonas(pageNumber, pageSize);

        // Verificar que el resultado sea igual a la lista simulada
        assertEquals(personasOutputDto, result);

        // Verificar que el servicio getAllPersonas se haya llamado con los parámetros correctos
        verify(personaService).getAllPersonas(pageNumber, pageSize);
    }

    private Iterable<PersonaOutputDTO> crearListaDePersonasOutputDto() {
        // Crea una lista de personas ficticia para simular la respuesta del servicio
        // Puedes personalizar esta lista según tus necesidades de prueba
        List<PersonaOutputDTO> personasOutputDto = new ArrayList<>();
        personasOutputDto.add(new PersonaOutputDTO(/* datos de la primera persona */));
        personasOutputDto.add(new PersonaOutputDTO(/* datos de la segunda persona */));
        // Agrega más personas si es necesario
        return personasOutputDto;
    }

    /**************************************** TESTEANDO getPersonByUsuario **************************************************/

    @Test
    public void testAddPersonaSuccess() {


        // Configura el comportamiento esperado del servicio simulado
        PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO();
        PersonaInputDTO personaInputDTO = new PersonaInputDTO();
        when(personaService.addPersona(personaInputDTO)).thenReturn(personaOutputDTO);

        // Ejecuta el método que deseas probar

        ResponseEntity<?> response = personaController.addPersona(personaInputDTO);

        // Verifica que se llame al servicio y que el código de estado sea OK
        verify(personaService, times(1)).addPersona(personaInputDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void testAddPersonaError() {
        // Configura el comportamiento esperado del servicio simulado para lanzar una excepción
        doThrow(UnprocessableEntityException.class).when(personaService).addPersona(any());

        // Ejecuta el método que deseas probar
        PersonaInputDTO inputDTO = new PersonaInputDTO(/* Proporciona los datos de entrada necesarios */);
        ResponseEntity<?> response = personaController.addPersona(inputDTO);

        // Verifica que se llame al servicio y que el código de estado sea UNPROCESSABLE_ENTITY
        verify(personaService, times(1)).addPersona(inputDTO);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

/****************************************TESTEANDO updatePersonaById**************************************************/

    @Test
    public void testUpdatePersonaById(){

        //Crear los objetos de persona entrada y salida
        PersonaInputDTO personaInputDTO = new PersonaInputDTO();
        PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO();

        personaInputDTO.setId_persona(1);

        //Configurar el comportamiento del servicio deseado
        when(personaService.updatePersona(personaInputDTO)).thenReturn(personaOutputDTO);


        //Llamando al servicio
        ResponseEntity<?> response = personaController.updatePersona(personaInputDTO);

        // Verifica que se llame al servicio y que el código de estado sea NOT_FOUND
        verify(personaService, times(1)).updatePersona(personaInputDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode()); //Comprobando que devuelve el código de estado

    }

    @Test
    public void testUpdatePersonaByIdNotFound(){
        int idPersona = 1; // Supongamos que este es el ID proporcionado en el objeto persona
        when(personaService.getPersonaId(1, "default"))
                .thenThrow(new EntityNotFoundException("No se ha encontrado la persona con el Id " + idPersona));

        // Ejecutar el método que deseas probar
        PersonaInputDTO personaInputDTO = new PersonaInputDTO();
        personaInputDTO.setId_persona(idPersona);
        ResponseEntity<?> response = personaController.updatePersona(personaInputDTO);

        // Verificar que se llamó al servicio para obtener el ID
        verify(personaService, times(1)).getPersonaId(eq(idPersona), anyString());

        // Verificar que la respuesta sea un ResponseEntity con el código de estado NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());


    }
    /****************************************TESTEANDO deletePersonaById**************************************************/

    @Test
    public void testDeletePersonaById() {
        // Configurar el comportamiento del servicio simulado
        Integer id = 1; // ID de persona que deseamos eliminar
        doNothing().when(personaService).deletePersonaById(1);


        // Ejecutar el método que deseas probar
        ResponseEntity<?> response = personaController.deletePersona(id);

        // Verificar que se llamó al servicio con el ID deseado
        verify(personaService, times(1)).deletePersonaById(id);

        // Verificar el código de estado y la respuesta
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Código de estado esperado
        assertEquals("El usuario con id: " + id + " ha sido eliminada correctamente", response.getBody()); // Respuesta esperada
    }

    @Test
    public void testDeletePersona() {
        // Configurar el comportamiento del servicio simulado
        Integer id = 1; // ID de persona que deseamos eliminar
        doThrow(new EntityNotFoundException("No se encontró el id: " + id + " para poder eliminar el elemento"))
                .when(personaService).deletePersonaById(id);

        // Ejecutar el método que deseas probar
        ResponseEntity<?> response = personaController.deletePersona(id);

        // Verificar que se llamó al servicio con el ID deseado
        verify(personaService, times(1)).deletePersonaById(id);

        // Verificar el código de estado y la respuesta
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); // Código de estado esperado

        // Verificar la respuesta personalizada en el body
        CustomError customError = (CustomError) response.getBody();
        assertEquals(customError, response.getBody());
    }

}
