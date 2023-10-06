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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class AsignaturaControllerTest {

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
    public void testGetAsignaturaById_Success() {
        int id = 1;

        AsignaturaOutputDTO asignaturaOutputDTO = new AsignaturaOutputDTO();
        when(asignaturaService.getAsignaturaById(id)).thenReturn(asignaturaOutputDTO);

        ResponseEntity<?> response = asignaturaController.getAsignaturaById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(asignaturaOutputDTO, response.getBody());

        verify(asignaturaService).getAsignaturaById(id);
    }

    @Test
    public void testGetAsignaturaById_EntityNotFoundException() {
        int id = 1;

        when(asignaturaService.getAsignaturaById(id)).thenThrow(new EntityNotFoundException("No se encontró la asignatura con ID: " + id));

        ResponseEntity<?> response = asignaturaController.getAsignaturaById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        CustomError customError = (CustomError) response.getBody();
        assertEquals("No se encontró la asignatura con ID: " + id, customError.getMensaje());
    }

    /**************************************** TESTEANDO getAllStudents **************************************************/

    @Test
    public void testGetAllStudents() {
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
    public void testDeleteAsignaturaById() {
        Integer id = 1;
        doNothing().when(asignaturaService).deleteAsignaturaById(1);

        ResponseEntity<?> response = asignaturaController.deleteAsignaturaById(id);

        verify(asignaturaService, times(1)).deleteAsignaturaById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode()); // Código de estado esperado
        assertEquals("La asignatura con id " + id + "ha sido eliminado", response.getBody()); // Respuesta esperada
    }

    @Test
    public void testDeleteAsignatura() {

        Integer id = 1;
        doThrow(new EntityNotFoundException("No se encontró el id: " + id + " para poder eliminar el elemento"))
                .when(asignaturaService).deleteAsignaturaById(id);

        ResponseEntity<?> response = asignaturaController.deleteAsignaturaById(id);

        verify(asignaturaService, times(1)).deleteAsignaturaById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); // Código de estado esperado

        CustomError customError = (CustomError) response.getBody();
        assertEquals(customError, response.getBody());
    }


    /****************************************TESTEANDO updateAsignaturaById**************************************************/

    @Test
    public void testUpdateAsignaturaById(){

        AsignaturaInputDTO asignaturaInputDTO = new AsignaturaInputDTO();
        AsignaturaOutputDTO asignaturaOutputDTO = new AsignaturaOutputDTO();

        asignaturaInputDTO.setIdAsignatura(1);

        when(asignaturaService.updateAsignatura(asignaturaInputDTO)).thenReturn(asignaturaOutputDTO);

        ResponseEntity<?> response = asignaturaController.updateAsignatura(asignaturaInputDTO);

        verify(asignaturaService, times(1)).updateAsignatura(asignaturaInputDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode()); //Comprobando que devuelve el código de estado

    }

    @Test
    public void testDeleteAsignaturaByIdError() {
        Integer invalidId = 2;

        doThrow(new EntityNotFoundException("La asignatura no fue encontrada")).when(asignaturaService).deleteAsignaturaById(invalidId);

        ResponseEntity<?> response = asignaturaController.deleteAsignaturaById(invalidId);

        verify(asignaturaService).deleteAsignaturaById(invalidId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

}
