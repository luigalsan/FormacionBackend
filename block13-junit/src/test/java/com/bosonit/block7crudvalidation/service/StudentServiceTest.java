package com.bosonit.block7crudvalidation.service;


import com.bosonit.block7crudvalidation.application.impl.StudentServiceImpl;
import com.bosonit.block7crudvalidation.controller.dto.Student.StudentInputDto;
import com.bosonit.block7crudvalidation.controller.dto.Student.StudentOutputDtoFull;
import com.bosonit.block7crudvalidation.controller.dto.Student.StudentOutputDtoSimple;
import com.bosonit.block7crudvalidation.entity.Asignatura;
import com.bosonit.block7crudvalidation.entity.Persona;
import com.bosonit.block7crudvalidation.entity.Student;
import com.bosonit.block7crudvalidation.error.EntityNotFoundException;
import com.bosonit.block7crudvalidation.repository.AsignaturaRepository;
import com.bosonit.block7crudvalidation.repository.PersonaRepository;
import com.bosonit.block7crudvalidation.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class StudentServiceTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private PersonaRepository personRepository;

    @Mock
    private AsignaturaRepository asignaturaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    /*************************************** TESTEANDO addPersona *****************************************************/

    @Test
    public void testAddStudent(){
        StudentInputDto studentInputDto = new StudentInputDto();
        studentInputDto.setId_persona(1);
        studentInputDto.setNum_hours_week(100);
        studentInputDto.setBranch("Física");

        Persona persona = new Persona();
        persona.setId_persona(1);
        when(personRepository.findById(1)).thenReturn(Optional.of(persona));

        Student student = new Student(studentInputDto);
        student.setPersona(persona);

        when(studentRepository.save(any())).thenReturn(student);

        studentService.addStudent(studentInputDto);

        assertNotNull(student.getPersona());
        assertEquals(1, student.getPersona().getId_persona());

    }

    /*************************************** TESTEANDO getStudentById *************************************************/

    @Test
    public void testGetStudentById(){

        Integer student_id = 1;
        Student student = new Student();
        student.setId_student(student_id);
        student.setPersona(new Persona());

        when(studentRepository.findById(student_id)).thenReturn(Optional.of(student));

        StudentOutputDtoFull result = studentService.getStudentByIdFull(student_id);

        assertNotNull(result);
        assertEquals(student_id, result.getId_student());

        verify(studentRepository, times(1)).findById(student_id);

    }

    @Test
    public void testGetStudentById_NotFound(){
        Integer studentIdNotFound = 1;
        when(studentRepository.findById(studentIdNotFound)).thenReturn(Optional.empty());

        // Ejecutar el método que deseas probar y esperar una excepción EntityNotFoundException
        assertThrows(EntityNotFoundException.class, () -> {
            studentService.getStudentByIdFull(studentIdNotFound);
        });

        // Verificar que se llamó al método findById con el ID deseado
        verify(studentRepository).findById(studentIdNotFound);
    }

    /**************************************** TESTEANDO getAllStudents ************************************************/


    @Test
    public void testGetAllStudents() {
        // Configurar el comportamiento esperado del repositorio simulado
        int pageNumber = 0;
        int pageSize = 10;

        // Crear una lista de Personas simuladas para el contenido de la página
        List<Student> studentsimulados = crearListaStudents(); // Define tu propia lista aquí

        // Crear un objeto Page simulado
        Page<Student> pageSimulado = new PageImpl<>(studentsimulados);

        when(studentRepository.findAll(any(PageRequest.class))).thenReturn(pageSimulado);

        // Ejecutar el método que deseas probar
        List<StudentOutputDtoSimple> outputDTOs = studentService.getAllStudents(pageNumber, pageSize);

        // Verificar que se llamó al método findAll con la configuración de PageRequest deseada
        verify(studentRepository).findAll(PageRequest.of(pageNumber, pageSize));

        // Verificar que el resultado no sea nulo y tenga la misma cantidad de elementos que la lista simulada
        assertNotNull(outputDTOs);
        assertEquals(studentsimulados.size(), outputDTOs.size());
    }

    private List<Student> crearListaStudents() {
        // Crea una lista de personas ficticia para simular el contenido de la página
        List<Student> students = new ArrayList<>();
        students.add(new Student());
        students.add(new Student());

        //Hay que asignar una persona previamente porque sino el test dará error ya que no detectará la persona
        for(Student student : students){
            student.setPersona(new Persona());
        }
        // Agrega más personas según sea necesario
        return students;
    }

    /*************************************** TESTEANDO updateStudent *************************************************/

    @Test
    public void testUpdateStudent() {
        // Configurar el comportamiento esperado del repositorio simulado
        Integer idStudent = 1; // El ID que deseamos actualizar
        StudentInputDto inputDTO = new StudentInputDto();
        inputDTO.setId_student(1);
        inputDTO.setBranch("Matemáticas"); // Nuevo valor para el apellido

        // Crear una instancia de Persona simulada con los datos iniciales
        Student studentInicial = new Student();
        studentInicial.setId_student(idStudent);
        studentInicial.setBranch("Matemáticas");
        studentInicial.setPersona(new Persona());

        // Simular que el estudiante existe en el repositorio a través del id y el usuario inicial
        when(studentRepository.findById(idStudent)).thenReturn(Optional.of(studentInicial));

        // Simular el comportamiento del método save en el repositorio simulado
        // Devuelve la misma instancia con el apellido actualizado
        when(studentRepository.save(any())).thenAnswer(invocation -> {
            Student studentGuardado = invocation.getArgument(0);
            return studentGuardado;
        });

        // Ejecutar el método que deseas probar
        StudentOutputDtoSimple outputDTO = studentService.updateStudent(inputDTO);

        // Verificar que se llamó al método findById con el ID deseado
        verify(studentRepository).findById(idStudent);

        // Verificar que se llamó al método save en el repositorio simulado
        verify(studentRepository).save(any());

        // Verificar que el resultado no sea nulo
        assertNotNull(outputDTO);

        // Verificar que el apellido en el resultado coincida con el valor actualizado
        assertEquals(inputDTO.getBranch(), outputDTO.getBranch());
    }


    @Test
    public void testUpdateStudentNotFound() {
        // Configurar el comportamiento esperado del repositorio simulado
        Integer idPersona = 1; // El ID que deseamos actualizar
        StudentInputDto inputDTO = new StudentInputDto();
        inputDTO.setId_student(idPersona);
        inputDTO.setBranch("Matemáticas"); // Nuevo valor para el usuario

        // Simular que no se encuentra ninguna persona con el ID proporcionado
        when(studentRepository.findById(idPersona)).thenReturn(Optional.empty());

        // Ejecutar el método que deseas probar y esperar que lance EntityNotFoundException
        assertThrows(EntityNotFoundException.class, () -> {
            studentService.updateStudent(inputDTO);
        });

        // Verificar que se llamó al método findById con el ID deseado
        verify(studentRepository).findById(idPersona);

        // Verificar que no se llamó al método save en el repositorio simulado
        verify(studentRepository, never()).save(any());
    }

    /*************************************** TESTEANDO deleteStudentById *************************************************/

    @Test
    public void testDeletePersonaById() {
        // Configurar el comportamiento esperado del repositorio simulado
        Integer idStudent = 1; // El ID que deseamos eliminar

        // Configurar el repositorio simulado para que devuelva un Student cuando se busque por el ID
        Student student = new Student();
        student.setId_student(idStudent);
        when(studentRepository.findById(idStudent)).thenReturn(Optional.of(student));

        // Ejecutar el método que deseas probar
        studentService.deleteStudentById(idStudent);

        // Verificar que se llamó al método findById con el ID deseado
        verify(studentRepository).findById(idStudent);

        // Verificar que se llamó al método deleteById con el ID de la Persona encontrada
        verify(studentRepository).deleteById(idStudent);
    }

    @Test
    public void testDeletePersonaByIdNotFound(){

        //Configurar el comportamiento esperado del repositorio simulado
        Integer studentId = 1;

        StudentInputDto inputDTO = new StudentInputDto();
        inputDTO.setId_persona(studentId);

        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());
        // Prueba que se lance EntityNotFoundException al intentar eliminar el ID inexistente
        assertThrows(EntityNotFoundException.class, () -> {
            studentService.deleteStudentById(studentId);
        });
    }

    /*************************************** TESTEANDO addAsignaturaToStudent *************************************************/

    @Test
    public void testAddAsignaturaToStudent() {
        Student student = new Student();

        when(studentRepository.findById(1)).thenReturn(java.util.Optional.of(student));
        when(asignaturaRepository.findById(2)).thenReturn(java.util.Optional.of(new Asignatura()));

        studentService.addAsignaturaToStudent(1, 2);

        verify(asignaturaRepository, times(1)).save(any());
    }


}
