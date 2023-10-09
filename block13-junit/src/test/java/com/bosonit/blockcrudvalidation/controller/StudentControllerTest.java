package com.bosonit.blockcrudvalidation.controller;

import com.bosonit.blockcrudvalidation.application.impl.StudentServiceImpl;
import com.bosonit.blockcrudvalidation.controller.dto.Asignatura.AsignaturaOutputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Student.StudentInputDto;
import com.bosonit.blockcrudvalidation.controller.dto.Student.StudentOutputDtoFull;
import com.bosonit.blockcrudvalidation.controller.dto.Student.StudentOutputDtoSimple;
import com.bosonit.blockcrudvalidation.error.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class StudentControllerTest {

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
     void testAddStudent() {

        StudentOutputDtoFull studentOutputDtoFull = new StudentOutputDtoFull();
        StudentInputDto studentInputDto = new StudentInputDto();
        when(studentService.addStudent(studentInputDto)).thenReturn(studentOutputDtoFull);

        ResponseEntity<?> response = studentController.addStudent(studentInputDto);

        verify(studentService, times(1)).addStudent(studentInputDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

   //Agregar testAddStudentError()

    /**************************************** TESTEANDO getStudentById **************************************************/

    @Test
     void testGetStudentByIdSimple() {
        StudentOutputDtoSimple studentOutputDtoSimple = new StudentOutputDtoSimple();

        int studentId = 1;
        String outputType = "simple";

        when(studentService.getStudentByIdSimple(studentId)).thenReturn(studentOutputDtoSimple);

        ResponseEntity<?> responseEntity = studentController.getStudentById(studentId, outputType);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
     void testGetStudentByIdFull() {
        StudentOutputDtoFull studentOutputDtoFull = new StudentOutputDtoFull();

        int studentId = 1;
        String outputType = "full";

        when(studentService.getStudentByIdFull(studentId)).thenReturn(studentOutputDtoFull);

        ResponseEntity<?> responseEntity = studentController.getStudentById(studentId, outputType);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
     void testGetStudentById_Simple_EntityNotFoundException() {
        Integer id = 1;
        String outputType = "simple";
        when(studentService.getStudentByIdSimple(id))
                .thenThrow(new EntityNotFoundException("No se encontró el estudiante con el id: " + id));

        ResponseEntity<?> responseEntity = studentController.getStudentById(1,outputType);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
     void testGetStudentById_Full_EntityNotFoundException() {
        Integer id = 1;
        String outputType = "full";
        when(studentService.getStudentByIdFull(id))
                .thenThrow(new EntityNotFoundException("No se encontró el estudiante con el id: " + id));

        ResponseEntity<?> responseEntity = studentController.getStudentById(1,outputType);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    /**************************************** TESTEANDO getAsignaturasByIdStudent( **************************************************/

    @Test
     void testGetAsignaturasByIdStudent() {
        List<AsignaturaOutputDTO> expectedAsignaturas = Arrays.asList();
        when(studentService.getAsignaturasByIdStudent(1)).thenReturn(expectedAsignaturas);

        Iterable<AsignaturaOutputDTO> result = studentController.getAsignaturasByIdStudent(1);

        verify(studentService).getAsignaturasByIdStudent(1);

        assertNotNull(result);
        assertEquals(expectedAsignaturas, result);

    }

    /**************************************** TESTEANDO updatePersona **************************************************/

    @Test
     void testUpdateStudent() {
        StudentOutputDtoSimple studentOutputDtoSimple = new StudentOutputDtoSimple();
        StudentInputDto validInput = new StudentInputDto();
        validInput.setIdPersona(1);

        when(studentService.updateStudent(validInput)).thenReturn(studentOutputDtoSimple);

        ResponseEntity<?> response = studentController.updateStudent(validInput);

        verify(studentService).updateStudent(validInput);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

   //Agregar testUpdateStudentError()

    /**************************************** TESTEANDO deleteStudentById **************************************************/

    @Test
     void testDeleteStudentById() {
        Integer validId = 1;

        doNothing().when(studentService).deleteStudentById(validId);

        ResponseEntity<?> response = studentController.deleteStudentById(validId);

        verify(studentService).deleteStudentById(validId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
     void testDeleteStudentByIdError() {
        Integer invalidId = 2;

        doThrow(new EntityNotFoundException("El estudiante no fue encontrado")).when(studentService).deleteStudentById(invalidId);

        ResponseEntity<?> response = studentController.deleteStudentById(invalidId);

        verify(studentService).deleteStudentById(invalidId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }


    /**************************************** TESTEANDO asignarAsignaturaToStudent **************************************************/

    @Test
     void testAsignarAsignaturaToStudent() {
        Integer studentId = 1;

        List<Integer> asignaturaIds = Arrays.asList(1, 2, 3);

        doNothing().when(studentService).asignarAsignaturasEstudiante(studentId, asignaturaIds);

        ResponseEntity<String> response = studentController.asignarAsignaturaToStudent(studentId, asignaturaIds);

        verify(studentService).asignarAsignaturasEstudiante(studentId, asignaturaIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
     void testAsignarAsignaturaToStudentError() {
        Integer studentId = 1;
        List<Integer> asignaturaIds = Arrays.asList(1, 2, 3);

        doThrow(new EntityNotFoundException("Ocurrió un fallo")).when(studentService).asignarAsignaturasEstudiante(studentId, asignaturaIds);

        ResponseEntity<String> response = studentController.asignarAsignaturaToStudent(studentId, asignaturaIds);

        verify(studentService).asignarAsignaturasEstudiante(studentId, asignaturaIds);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    /**************************************** TESTEANDO desasignarAsignaturasEstudiante **************************************************/

    @Test
     void testDesasignarAsignaturaToStudent() {
        Integer studentId = 1;
        List<Integer> asignaturaIds = Arrays.asList(1, 2, 3);

        doNothing().when(studentService).desasignarAsignaturasEstudiante(studentId, asignaturaIds);

        ResponseEntity<String> response = studentController.desasignarAsignaturaToStudent(studentId, asignaturaIds);

        verify(studentService).desasignarAsignaturasEstudiante(studentId, asignaturaIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
     void testDesasignarAsignaturaToStudentError() {
        Integer studentId = 1;
        List<Integer> asignaturaIds = Arrays.asList(1, 2, 3);

        doThrow(new EntityNotFoundException("Ocurrió un fallo")).when(studentService).desasignarAsignaturasEstudiante(studentId, asignaturaIds);

        ResponseEntity<String> response = studentController.desasignarAsignaturaToStudent(studentId, asignaturaIds);

        verify(studentService).desasignarAsignaturasEstudiante(studentId, asignaturaIds);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    /**************************************** TESTEANDO addAsignaturaToStudent **************************************************/

    @Test
     void testAddAsignaturaToStudent() {
        Integer studentId = 1;
        Integer asignaturaId = 2;

        doNothing().when(studentService).addAsignaturaToStudent(studentId, asignaturaId);

        ResponseEntity<String> response = studentController.addAsignaturatoStudent(studentId, asignaturaId);

        verify(studentService).addAsignaturaToStudent(studentId, asignaturaId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
     void testAddAsignaturaToStudentError() {
        Integer studentId = 1;
        Integer asignaturaId = 2;

        doThrow(new EntityNotFoundException("Ocurrió un fallo")).when(studentService).addAsignaturaToStudent(studentId, asignaturaId);

        ResponseEntity<String> response = studentController.addAsignaturatoStudent(studentId, asignaturaId);

        verify(studentService).addAsignaturaToStudent(studentId, asignaturaId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}
