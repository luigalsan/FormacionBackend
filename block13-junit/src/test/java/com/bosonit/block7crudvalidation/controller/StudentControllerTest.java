package com.bosonit.block7crudvalidation.controller;

import com.bosonit.block7crudvalidation.application.impl.StudentServiceImpl;
import com.bosonit.block7crudvalidation.controller.dto.Student.StudentInputDto;
import com.bosonit.block7crudvalidation.controller.dto.Student.StudentOutputDtoFull;
import com.bosonit.block7crudvalidation.error.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentServiceImpl studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**************************************** TESTEANDO addStudent **************************************************/

    @Test
    public void testAddStudent_Success() {

        // Configura el comportamiento esperado del servicio simulado
        StudentOutputDtoFull studentOutputDtoFull = new StudentOutputDtoFull();
        StudentInputDto studentInputDto = new StudentInputDto();
        when(studentService.addStudent(studentInputDto)).thenReturn(studentOutputDtoFull);

        // Ejecuta el método que deseas probar
        ResponseEntity<?> response = studentController.addStudent(studentInputDto);

        // Verifica que se llame al servicio y que el código de estado sea OK
        verify(studentService, times(1)).addStudent(studentInputDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testAddStudentError() {
        // Configura el comportamiento esperado del servicio simulado para lanzar una excepción
        doThrow(EntityNotFoundException.class).when(studentService).addStudent(any());

        // Ejecuta el método que deseas probar
        StudentInputDto inputDTO = new StudentInputDto(/* Proporciona los datos de entrada necesarios */);
        ResponseEntity<?> response = studentController.addStudent(inputDTO);

        // Verifica que se llame al servicio y que el código de estado sea UNPROCESSABLE_ENTITY
        verify(studentService, times(1)).addStudent(inputDTO);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }




}
