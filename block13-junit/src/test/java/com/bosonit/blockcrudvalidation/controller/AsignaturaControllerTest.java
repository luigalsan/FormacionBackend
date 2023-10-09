package com.bosonit.blockcrudvalidation.controller;

import com.bosonit.blockcrudvalidation.application.impl.AsignaturaServiceImpl;
import com.bosonit.blockcrudvalidation.controller.dto.Asignatura.AsignaturaInputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Asignatura.AsignaturaOutputDTO;
import com.bosonit.blockcrudvalidation.error.CustomError;
import com.bosonit.blockcrudvalidation.error.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class AsignaturaControllerTest {

    @InjectMocks
    private AsignaturaController asignaturaController;
    @Mock
    private AsignaturaServiceImpl asignaturaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**************************************** TESTEANDO getAsignaturaById **************************************************/

    @Test
     void testGetAsignaturaById_Success() {
        int id = 1;

        AsignaturaOutputDTO asignaturaOutputDTO = new AsignaturaOutputDTO();
        when(asignaturaService.getAsignaturaById(id)).thenReturn(asignaturaOutputDTO);

        ResponseEntity<AsignaturaOutputDTO> response = asignaturaController.getAsignaturaById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(asignaturaOutputDTO, response.getBody());

        verify(asignaturaService).getAsignaturaById(id);
    }

    @Test
     void testGetAsignaturaById_EntityNotFoundException() {
        int id = 1;
        String errorMessage = "No se encontró la asignatura con ID: " + id;
        when(asignaturaService.getAsignaturaById(id)).thenThrow(new EntityNotFoundException(errorMessage));

        // Ejecuta la prueba
        try {
            asignaturaController.getAsignaturaById(id);
        } catch (ResponseStatusException e) {
            // Verifica que el código de estado sea HttpStatus.NOT_FOUND
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());

            // Verifica que el mensaje de error sea igual al mensaje de error simulado
            assertEquals(errorMessage, e.getReason());
        }
    }

    /**************************************** TESTEANDO getAllStudents **************************************************/

    @Test
     void testGetAllStudents() {
        int pageNumber = 0;
        int pageSize = 4;
        Iterable<AsignaturaOutputDTO> asignaturaOutputDTOS = crearListaAsignaturaOutputDto();
        when(asignaturaService.getAllAsignaturas(pageNumber, pageSize)).thenReturn((List<AsignaturaOutputDTO>) asignaturaOutputDTOS);

        Iterable<AsignaturaOutputDTO> result = asignaturaController.getAllAsignaturas(pageNumber, pageSize);

        assertEquals(asignaturaOutputDTOS, result);

        verify(asignaturaService).getAllAsignaturas(pageNumber, pageSize);
    }

    private Iterable<AsignaturaOutputDTO> crearListaAsignaturaOutputDto() {

        List<AsignaturaOutputDTO> asignaturaOutputDTOS = new ArrayList<>();
        asignaturaOutputDTOS.add(new AsignaturaOutputDTO());
        asignaturaOutputDTOS.add(new AsignaturaOutputDTO());
        // Agrega más personas si es necesario
        return asignaturaOutputDTOS;
    }


    /****************************************TESTEANDO deleteAsignaturaById**************************************************/

    @Test
     void testDeleteAsignaturaById() {
        Integer id = 1;
        doNothing().when(asignaturaService).deleteAsignaturaById(1);

        ResponseEntity<Object> response = asignaturaController.deleteAsignaturaById(id);

        verify(asignaturaService, times(1)).deleteAsignaturaById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode()); // Código de estado esperado
        assertEquals("La asignatura con id " + id + "ha sido eliminado", response.getBody()); // Respuesta esperada
    }

    @Test
     void testDeleteAsignatura() {

        Integer id = 1;
        doThrow(new EntityNotFoundException("No se encontró el id: " + id + " para poder eliminar el elemento"))
                .when(asignaturaService).deleteAsignaturaById(id);

        ResponseEntity<Object> response = asignaturaController.deleteAsignaturaById(id);

        verify(asignaturaService, times(1)).deleteAsignaturaById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); // Código de estado esperado

        CustomError customError = (CustomError) response.getBody();
        assertEquals(customError, response.getBody());
    }


    /****************************************TESTEANDO updateAsignaturaById**************************************************/

    @Test
     void testUpdateAsignaturaById(){

        AsignaturaInputDTO asignaturaInputDTO = new AsignaturaInputDTO();
        AsignaturaOutputDTO asignaturaOutputDTO = new AsignaturaOutputDTO();

        asignaturaInputDTO.setIdAsignatura(1);

        when(asignaturaService.updateAsignatura(asignaturaInputDTO)).thenReturn(asignaturaOutputDTO);

        ResponseEntity<AsignaturaOutputDTO> response = asignaturaController.updateAsignatura(asignaturaInputDTO);

        verify(asignaturaService, times(1)).updateAsignatura(asignaturaInputDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode()); //Comprobando que devuelve el código de estado

    }

    @Test
     void testDeleteAsignaturaByIdError() {
        Integer invalidId = 2;

        doThrow(new EntityNotFoundException("La asignatura no fue encontrada")).when(asignaturaService).deleteAsignaturaById(invalidId);

        ResponseEntity<Object> response = asignaturaController.deleteAsignaturaById(invalidId);

        verify(asignaturaService).deleteAsignaturaById(invalidId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

}
