package com.bosonit.block7crudvalidation.service;

import com.bosonit.block7crudvalidation.application.impl.ProfesorServiceImpl;
import com.bosonit.block7crudvalidation.controller.dto.Profesor.ProfesorInputDTO;
import com.bosonit.block7crudvalidation.controller.dto.Profesor.ProfesorOutputDTO;
import com.bosonit.block7crudvalidation.entity.Persona;
import com.bosonit.block7crudvalidation.entity.Profesor;
import com.bosonit.block7crudvalidation.entity.Student;
import com.bosonit.block7crudvalidation.error.EntityNotFoundException;
import com.bosonit.block7crudvalidation.repository.PersonaRepository;
import com.bosonit.block7crudvalidation.repository.ProfesorRepository;
import com.bosonit.block7crudvalidation.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class ProfesorServiceTest {


    @InjectMocks
    ProfesorServiceImpl profesorService;

    @Mock
    ProfesorRepository profesorRepository;

    @Mock
    PersonaRepository personaRepository;

    @Mock
    StudentRepository studentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    /*************************************** TESTEANDO addProfesor *****************************************************/

    @Test
    public void testAddProfesor() {

        ProfesorInputDTO inputDTO = new ProfesorInputDTO();
        inputDTO.setId_persona(1);
        inputDTO.setBranch("Física");

        Persona persona = new Persona();
        persona.setId_persona(1);
        when(personaRepository.findById(1)).thenReturn(Optional.of(persona));

        Profesor profesor = new Profesor(inputDTO);
        profesor.setPersona(persona);

        when(profesorRepository.save(any())).thenReturn(profesor);

        profesorService.addProfesor(inputDTO);

        assertNotNull(profesor.getPersona());
        assertEquals(1, profesor.getPersona().getId_persona());
    }

    /*************************************** TESTEANDO getProfesorById *************************************************/

    @Test
    public void testGetProfesorById(){

        Integer id = 1;
        Profesor profesor = new Profesor();
        profesor.setId_profesor(id);
        profesor.setPersona(new Persona());

        when(profesorRepository.findById(id)).thenReturn(Optional.of(profesor));

        ProfesorOutputDTO result = profesorService.getProfesorById(id);

        assertNotNull(result);
        assertEquals(id, result.getId_profesor());

        verify(profesorRepository, times(1)).findById(id);

    }

    @Test
    public void testGetProfesorById_NotFound(){
        Integer studentIdNotFound = 1;
        when(profesorRepository.findById(studentIdNotFound)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            profesorService.getProfesorById(studentIdNotFound);
        });

        verify(profesorRepository).findById(studentIdNotFound);
    }

    /**************************************** TESTEANDO getAllProfesor ************************************************/

    @Test
    public void testGetAllProfesor() {
        int pageNumber = 0;
        int pageSize = 10;

        List<Profesor> profesorList = crearListProfesores(); // Define tu propia lista aquí

        Page<Profesor> pageSimulado = new PageImpl<>(profesorList);

        when(profesorRepository.findAll(any(PageRequest.class))).thenReturn(pageSimulado);

        List<ProfesorOutputDTO> outputDTOs = (List<ProfesorOutputDTO>) profesorService.getAllProfesor(pageNumber, pageSize);

        verify(profesorRepository).findAll(PageRequest.of(pageNumber, pageSize));

        assertNotNull(outputDTOs);
        assertEquals(profesorList.size(), outputDTOs.size());
    }

    private List<Profesor> crearListProfesores() {
        List<Profesor> profesors = new ArrayList<>();
        profesors.add(new Profesor());
        profesors.add(new Profesor());

        for(Profesor profesor : profesors){
            profesor.setPersona(new Persona());
        }
        return profesors;
    }

    /**************************************** TESTEANDO updateProfesor ************************************************/

    @Test
    public void testUpdateStudent() {
        Integer idProfesor = 1;
        ProfesorInputDTO inputDTO = new ProfesorInputDTO();
        inputDTO.setId_profesor(1);
        inputDTO.setBranch("Matemáticas");

        Profesor profesorInicial = new Profesor();
        profesorInicial.setId_profesor(idProfesor);
        profesorInicial.setBranch("Matemáticas");
        profesorInicial.setPersona(new Persona());

        when(profesorRepository.findById(idProfesor)).thenReturn(Optional.of(profesorInicial));
        when(profesorRepository.save(any())).thenAnswer(invocation -> {
            Profesor profesorGuardado = invocation.getArgument(0);
            return profesorGuardado;
        });

        ProfesorOutputDTO outputDTO = profesorService.updateProfesor(inputDTO);

        verify(profesorRepository).findById(idProfesor);
        verify(profesorRepository).save(any());

        assertNotNull(outputDTO);
        assertEquals(inputDTO.getBranch(), outputDTO.getBranch());
    }

    @Test
    public void testUpdateStudentNotFound() {

        Integer idProfesor = 1;
        ProfesorInputDTO inputDTO = new ProfesorInputDTO();
        inputDTO.setId_profesor(idProfesor);
        inputDTO.setBranch("Matemáticas");

        when(profesorRepository.findById(idProfesor)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            profesorService.updateProfesor(inputDTO);
        });

        verify(profesorRepository).findById(idProfesor);
        verify(profesorRepository, never()).save(any());
    }

    /*************************************** TESTEANDO addStudentToProfesor *************************************************/

    @Test
    public void testAddStudentToProfesor() {
        Integer id_profesor = 1;
        Integer id_student = 2;

        Profesor profesor = new Profesor();
        profesor.setId_profesor(id_profesor);
        profesor.setStudents(new HashSet<>());

        Student student = new Student();
        student.setId_student(id_student);

        when(profesorRepository.findById(id_profesor)).thenReturn(Optional.of(profesor));
        when(studentRepository.findById(id_student)).thenReturn(Optional.of(student));

        profesorService.addStudentToProfesor(id_student, id_profesor);

        verify(profesorRepository, times(1)).findById(id_profesor);
        verify(studentRepository, times(1)).findById(id_student);

        verify(profesorRepository, times(1)).save(profesor);

        assertTrue(profesor.getStudents().contains(student));
    }


    /*************************************** TESTEANDO deleteProfesorById *************************************************/

    @Test
    public void testDeleteProfesorById() {
        Integer idProfesor = 1;

        Profesor profesor = new Profesor();
        profesor.setId_profesor(idProfesor);
        when(profesorRepository.findById(idProfesor)).thenReturn(Optional.of(profesor));

        profesorService.deleteProfessorById(idProfesor);

        verify(profesorRepository).findById(idProfesor);
        verify(profesorRepository).deleteById(idProfesor);
    }

    @Test
    public void testDeleteStudentByIdNotFound(){

        Integer profesorId = 1;

        ProfesorInputDTO inputDTO = new ProfesorInputDTO();
        inputDTO.setId_profesor(profesorId);

        when(profesorRepository.findById(profesorId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            profesorService.deleteProfessorById(profesorId);
        });
    }


}
