package com.bosonit.blockcrudvalidation.service;

import com.bosonit.blockcrudvalidation.application.impl.ProfesorServiceImpl;
import com.bosonit.blockcrudvalidation.controller.dto.Profesor.ProfesorInputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Profesor.ProfesorOutputDTO;
import com.bosonit.blockcrudvalidation.entity.Persona;
import com.bosonit.blockcrudvalidation.entity.Profesor;
import com.bosonit.blockcrudvalidation.entity.Student;
import com.bosonit.blockcrudvalidation.error.EntityNotFoundException;
import com.bosonit.blockcrudvalidation.repository.PersonaRepository;
import com.bosonit.blockcrudvalidation.repository.ProfesorRepository;
import com.bosonit.blockcrudvalidation.repository.StudentRepository;
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


class ProfesorServiceTest {


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
     void testAddProfesor() {

        ProfesorInputDTO inputDTO = new ProfesorInputDTO();
        inputDTO.setIdPersona(1);
        inputDTO.setBranch("Física");

        Persona persona = new Persona();
        persona.setIdPersona(1);
        when(personaRepository.findById(1)).thenReturn(Optional.of(persona));

        Profesor profesor = new Profesor(inputDTO);
        profesor.setPersona(persona);

        when(profesorRepository.save(any())).thenReturn(profesor);

        profesorService.addProfesor(inputDTO);

        assertNotNull(profesor.getPersona());
        assertEquals(1, profesor.getPersona().getIdPersona());
    }

    /*************************************** TESTEANDO getProfesorById *************************************************/

    @Test
     void testGetProfesorById(){

        Integer id = 1;
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(id);
        profesor.setPersona(new Persona());

        when(profesorRepository.findById(id)).thenReturn(Optional.of(profesor));

        ProfesorOutputDTO result = profesorService.getProfesorById(id);

        assertNotNull(result);
        assertEquals(id, result.getIdProfesor());

        verify(profesorRepository, times(1)).findById(id);

    }

    @Test
     void testGetProfesorById_NotFound(){
        Integer studentIdNotFound = 1;
        when(profesorRepository.findById(studentIdNotFound)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            profesorService.getProfesorById(studentIdNotFound);
        });

        verify(profesorRepository).findById(studentIdNotFound);
    }

    /**************************************** TESTEANDO getAllProfesor ************************************************/

    @Test
     void testGetAllProfesor() {
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
     void testUpdateStudent() {
        Integer idProfesor = 1;
        ProfesorInputDTO inputDTO = new ProfesorInputDTO();
        inputDTO.setIdProfesor(1);
        inputDTO.setBranch("Matemáticas");

        Profesor profesorInicial = new Profesor();
        profesorInicial.setIdProfesor(idProfesor);
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
     void testUpdateStudentNotFound() {

        Integer idProfesor = 1;
        ProfesorInputDTO inputDTO = new ProfesorInputDTO();
        inputDTO.setIdProfesor(idProfesor);
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
     void testAddStudentToProfesor() {
        Integer id_profesor = 1;
        Integer id_student = 2;

        Profesor profesor = new Profesor();
        profesor.setIdProfesor(id_profesor);
        profesor.setStudents(new HashSet<>());

        Student student = new Student();
        student.setIdStudent(id_student);

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
     void testDeleteProfesorById() {
        Integer idProfesor = 1;

        Profesor profesor = new Profesor();
        profesor.setIdProfesor(idProfesor);
        when(profesorRepository.findById(idProfesor)).thenReturn(Optional.of(profesor));

        profesorService.deleteProfessorById(idProfesor);

        verify(profesorRepository).findById(idProfesor);
        verify(profesorRepository).deleteById(idProfesor);
    }

    @Test
     void testDeleteStudentByIdNotFound(){

        Integer profesorId = 1;

        ProfesorInputDTO inputDTO = new ProfesorInputDTO();
        inputDTO.setIdProfesor(profesorId);

        when(profesorRepository.findById(profesorId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            profesorService.deleteProfessorById(profesorId);
        });
    }


}
