package com.bosonit.block7crudvalidation.controller;

import com.bosonit.block7crudvalidation.application.impl.StudentServiceImpl;
import com.bosonit.block7crudvalidation.controller.dto.Asignatura.AsignaturaOutputDTO;
import com.bosonit.block7crudvalidation.controller.dto.Persona.PersonaOutputDTO;
import com.bosonit.block7crudvalidation.controller.dto.Student.StudentInputDto;
import com.bosonit.block7crudvalidation.controller.dto.Student.StudentOutputDtoFull;
import com.bosonit.block7crudvalidation.controller.dto.Student.StudentOutputDtoSimple;
import com.bosonit.block7crudvalidation.entity.Asignatura;
import com.bosonit.block7crudvalidation.entity.Student;
import com.bosonit.block7crudvalidation.error.EntityNotFoundException;
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

        StudentOutputDtoFull studentOutputDtoFull = new StudentOutputDtoFull();
        StudentInputDto studentInputDto = new StudentInputDto();
        when(studentService.addStudent(studentInputDto)).thenReturn(studentOutputDtoFull);

        ResponseEntity<?> response = studentController.addStudent(studentInputDto);

        verify(studentService, times(1)).addStudent(studentInputDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testAddStudentError() {
        doThrow(EntityNotFoundException.class).when(studentService).addStudent(any());

        StudentInputDto inputDTO = new StudentInputDto(/* Proporciona los datos de entrada necesarios */);
        ResponseEntity<?> response = studentController.addStudent(inputDTO);

        verify(studentService, times(1)).addStudent(inputDTO);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**************************************** TESTEANDO getStudentById **************************************************/

    @Test
    public void testGetStudentByIdSimple() {
        StudentOutputDtoSimple studentOutputDtoSimple = new StudentOutputDtoSimple();

        int studentId = 1;
        String outputType = "simple";

        when(studentService.getStudentByIdSimple(studentId)).thenReturn(studentOutputDtoSimple);

        ResponseEntity<?> responseEntity = studentController.getStudentById(studentId, outputType);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetStudentByIdFull() {
        StudentOutputDtoFull studentOutputDtoFull = new StudentOutputDtoFull();

        int studentId = 1;
        String outputType = "full";

        when(studentService.getStudentByIdFull(studentId)).thenReturn(studentOutputDtoFull);

        ResponseEntity<?> responseEntity = studentController.getStudentById(studentId, outputType);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetStudentById_Simple_EntityNotFoundException() {
        Integer id = 1;
        String outputType = "simple";
        when(studentService.getStudentByIdSimple(id))
                .thenThrow(new EntityNotFoundException("No se encontró el estudiante con el id: " + id));

        ResponseEntity<?> responseEntity = studentController.getStudentById(1,outputType);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testGetStudentById_Full_EntityNotFoundException() {
        Integer id = 1;
        String outputType = "full";
        when(studentService.getStudentByIdFull(id))
                .thenThrow(new EntityNotFoundException("No se encontró el estudiante con el id: " + id));

        ResponseEntity<?> responseEntity = studentController.getStudentById(1,outputType);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    /**************************************** TESTEANDO getAsignaturasByIdStudent( **************************************************/

    @Test
    public void testGetAsignaturasByIdStudent() {
        List<AsignaturaOutputDTO> expectedAsignaturas = Arrays.asList();
        when(studentService.getAsignaturasByIdStudent(1)).thenReturn(expectedAsignaturas);

        Iterable<AsignaturaOutputDTO> result = studentController.getAsignaturasByIdStudent(1);

        verify(studentService).getAsignaturasByIdStudent(1);

        assertNotNull(result);
        assertEquals(expectedAsignaturas, result);

    }

    /**************************************** TESTEANDO updatePersona **************************************************/

    @Test
    public void testUpdatePersona() {
        StudentOutputDtoSimple studentOutputDtoSimple = new StudentOutputDtoSimple();
        StudentInputDto validInput = new StudentInputDto();
        validInput.setId_student(1);

        when(studentService.updateStudent(validInput)).thenReturn(studentOutputDtoSimple);

        ResponseEntity<?> response = studentController.updateStudent(validInput);

        verify(studentService).getStudentByIdSimple(1);

        verify(studentService).updateStudent(validInput);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateStudentError() {
        Integer id = 1;
        StudentInputDto invalidInput = new StudentInputDto();
        invalidInput.setId_student(id);

        when(studentService.getStudentByIdSimple(1)).thenThrow(new EntityNotFoundException("No se encontró el estudiante con el id: " + id));

        ResponseEntity<?> response = studentController.updateStudent(invalidInput);

        verify(studentService).getStudentByIdSimple(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    /**************************************** TESTEANDO deletePersona **************************************************/

    @Test
    public void testDeleteStudentById() {
        Integer validId = 1;

        doNothing().when(studentService).deleteStudentById(validId);

        ResponseEntity<?> response = studentController.deleteStudentById(validId);

        verify(studentService).deleteStudentById(validId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    /**************************************** TESTEANDO deleteStudentById **************************************************/

    @Test
    public void testDeleteStudentByIdError() {
        Integer invalidId = 2;

        doThrow(new EntityNotFoundException("El estudiante no fue encontrado")).when(studentService).deleteStudentById(invalidId);

        ResponseEntity<?> response = studentController.deleteStudentById(invalidId);

        verify(studentService).deleteStudentById(invalidId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }


    /**************************************** TESTEANDO asignarAsignaturaToStudent **************************************************/

    @Test
    public void testAsignarAsignaturaToStudent() {
        Integer studentId = 1;

        List<Integer> asignaturaIds = Arrays.asList(1, 2, 3);

        doNothing().when(studentService).asignarAsignaturasEstudiante(studentId, asignaturaIds);

        ResponseEntity<String> response = studentController.asignarAsignaturaToStudent(studentId, asignaturaIds);

        verify(studentService).asignarAsignaturasEstudiante(studentId, asignaturaIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testAsignarAsignaturaToStudentError() {
        Integer studentId = 1;
        List<Integer> asignaturaIds = Arrays.asList(1, 2, 3);

        doThrow(new EntityNotFoundException("Ocurrió un fallo")).when(studentService).asignarAsignaturasEstudiante(studentId, asignaturaIds);

        ResponseEntity<String> response = studentController.asignarAsignaturaToStudent(studentId, asignaturaIds);

        verify(studentService).asignarAsignaturasEstudiante(studentId, asignaturaIds);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    /**************************************** TESTEANDO desasignarAsignaturasEstudiante **************************************************/

    @Test
    public void testDesasignarAsignaturaToStudent() {
        Integer studentId = 1;
        List<Integer> asignaturaIds = Arrays.asList(1, 2, 3);

        doNothing().when(studentService).desasignarAsignaturasEstudiante(studentId, asignaturaIds);

        ResponseEntity<String> response = studentController.desasignarAsignaturaToStudent(studentId, asignaturaIds);

        verify(studentService).desasignarAsignaturasEstudiante(studentId, asignaturaIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void testDesasignarAsignaturaToStudentError() {
        Integer studentId = 1;
        List<Integer> asignaturaIds = Arrays.asList(1, 2, 3);

        doThrow(new EntityNotFoundException("Ocurrió un fallo")).when(studentService).desasignarAsignaturasEstudiante(studentId, asignaturaIds);

        ResponseEntity<String> response = studentController.desasignarAsignaturaToStudent(studentId, asignaturaIds);

        verify(studentService).desasignarAsignaturasEstudiante(studentId, asignaturaIds);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    /**************************************** TESTEANDO addAsignaturaToStudent **************************************************/

    @Test
    public void testAddAsignaturatoStudent() {
        Integer studentId = 1;
        Integer asignaturaId = 2;

        doNothing().when(studentService).addAsignaturaToStudent(studentId, asignaturaId);

        ResponseEntity<String> response = studentController.addAsignaturatoStudent(studentId, asignaturaId);

        verify(studentService).addAsignaturaToStudent(studentId, asignaturaId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testAddAsignaturatoStudentError() {
        Integer studentId = 1;
        Integer asignaturaId = 2;

        doThrow(new EntityNotFoundException("Ocurrió un fallo")).when(studentService).addAsignaturaToStudent(studentId, asignaturaId);

        ResponseEntity<String> response = studentController.addAsignaturatoStudent(studentId, asignaturaId);

        verify(studentService).addAsignaturaToStudent(studentId, asignaturaId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}
