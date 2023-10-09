package com.bosonit.blockcrudvalidation.controller;

import com.bosonit.blockcrudvalidation.application.impl.ProfesorServiceImpl;
import com.bosonit.blockcrudvalidation.controller.dto.Profesor.ProfesorInputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Profesor.ProfesorOutputDTO;
import com.bosonit.blockcrudvalidation.error.CustomError;
import com.bosonit.blockcrudvalidation.error.EntityNotFoundException;
import com.bosonit.blockcrudvalidation.error.UnprocessableEntityException;
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

class ProfesorControllerTest {

    @InjectMocks
    ProfesorController profesorController;

    @Mock
    ProfesorServiceImpl profesorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**************************************** TESTEANDO addProfesor **************************************************/

    @Test
     void testAddProfesor() {

        ProfesorOutputDTO profesorOutputDTO = new ProfesorOutputDTO();
        ProfesorInputDTO profesorInputDTO = new ProfesorInputDTO();
        when(profesorService.addProfesor(profesorInputDTO)).thenReturn(profesorOutputDTO);

        ResponseEntity<?> response = profesorController.addProfesor(profesorInputDTO);

        verify(profesorService, times(1)).addProfesor(profesorInputDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    //Agregar testAddProfesorError() {

    /**************************************** TESTEANDO getProfesorById **************************************************/

    @Test
     void testGetProfesorById() {
        ProfesorOutputDTO profesorOutputDTO = new ProfesorOutputDTO();

        int studentId = 1;

        when(profesorService.getProfesorById(studentId)).thenReturn(profesorOutputDTO);

        ResponseEntity<?> responseEntity = profesorController.getProfesorById(studentId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    //Agregar testGetProfesorById_EntityNotFoundException()


    /**************************************** TESTEANDO getAllProfesores **************************************************/

    @Test
     void testGetAllProfesores() {
        int pageNumber = 0;
        int pageSize = 4;
        Iterable<ProfesorOutputDTO> profesorOutputDTOS = crearListaDeProfesoresOutputDto();
        when(profesorService.getAllProfesor(pageNumber, pageSize)).thenReturn(profesorOutputDTOS);

        Iterable<ProfesorOutputDTO> result = profesorController.getAllProfesores(pageNumber, pageSize);

        assertEquals(profesorOutputDTOS, result);

        verify(profesorService).getAllProfesor(pageNumber, pageSize);
    }

    private Iterable<ProfesorOutputDTO> crearListaDeProfesoresOutputDto() {
        List<ProfesorOutputDTO> profesorOutputDTOS = new ArrayList<>();
        profesorOutputDTOS.add(new ProfesorOutputDTO());
        profesorOutputDTOS.add(new ProfesorOutputDTO());
        // Agrega más personas si es necesario
        return profesorOutputDTOS;
    }

    /**************************************** TESTEANDO updateProfesor **************************************************/

    @Test
     void testUpdateProfesor() {
        ProfesorOutputDTO profesorOutputDTO = new ProfesorOutputDTO();
        ProfesorInputDTO validInput = new ProfesorInputDTO();
        validInput.setIdProfesor(1);

        when(profesorService.updateProfesor(validInput)).thenReturn(profesorOutputDTO);

        ResponseEntity<?> response = profesorController.updateProfesor(validInput);

        verify(profesorService).updateProfesor(validInput);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    //Agregar testUpdateProfesorError()

    /**************************************** TESTEANDO deleteProfesorById **************************************************/

    @Test
     void testDeleteProfesorById() {
        Integer validId = 1;

        doNothing().when(profesorService).deleteProfessorById(validId);

        ResponseEntity<?> response = profesorController.deleteProfesorById(validId);

        verify(profesorService).deleteProfessorById(validId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
     void testDeleteProfesorByIdError() {
        Integer invalidId = 2;

        doThrow(new EntityNotFoundException("El estudiante no fue encontrado")).when(profesorService).deleteProfessorById(invalidId);

        ResponseEntity<?> response = profesorController.deleteProfesorById(invalidId);

        verify(profesorService).deleteProfessorById(invalidId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    /**************************************** TESTEANDO testAddStudentProfesor **************************************************/

    @Test
     void testAddStudentProfesor() {
        Integer studentId = 1;
        Integer profesorId = 2;

        doNothing().when(profesorService).addStudentToProfesor(studentId, profesorId);

        ResponseEntity<String> response = profesorController.addStudentProfesor(studentId, profesorId);

        verify(profesorService).addStudentToProfesor(studentId, profesorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
     void testAddStudentProfesorError() {
        Integer studentId = 1;
        Integer profesorId = 2;

        doThrow(new EntityNotFoundException("Ocurrió un fallo")).when(profesorService).addStudentToProfesor(studentId, profesorId);

        ResponseEntity<String> response = profesorController.addStudentProfesor(studentId, profesorId);

        verify(profesorService).addStudentToProfesor(studentId, profesorId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}
