package com.bosonit.blockcrudvalidation.service;

import com.bosonit.blockcrudvalidation.application.impl.PersonaServiceImpl;
import com.bosonit.blockcrudvalidation.controller.dto.Persona.PersonaInputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Persona.PersonaOutputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Persona.PersonaProfesorOutputDto;
import com.bosonit.blockcrudvalidation.controller.dto.Persona.PersonaStudentOutputDto;
import com.bosonit.blockcrudvalidation.entity.Persona;
import com.bosonit.blockcrudvalidation.entity.Profesor;
import com.bosonit.blockcrudvalidation.entity.Student;
import com.bosonit.blockcrudvalidation.error.EntityNotFoundException;
import com.bosonit.blockcrudvalidation.repository.PersonaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PersonaServiceTest {

    @InjectMocks
    private PersonaServiceImpl personaService; //Esto permite hacer uso de los mock. En este caso, el repositorio "simulado" de la siguiente línea

    @Mock
    private PersonaRepository personaRepository; //Esto crea un repositorio simulado o mock

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /****************************************TESTEANDO addPersona******************************************************/

    @Test
    public void testAddPersona() {
// Configuración del inputDto con valores conocidos
        PersonaInputDTO inputDto = new PersonaInputDTO();
        inputDto.setUsuario("luis1991");

// Configuración del mock para que devuelva un objeto Persona con valores conocidos
        Persona personaConValoresConocidos = new Persona();

        personaConValoresConocidos.setUsuario("luis1991");
        when(personaRepository.save(any())).thenReturn(personaConValoresConocidos);
// Ejecuta el método que deseas probar
        PersonaOutputDTO outputDto = personaService.addPersona(inputDto);

    // Verifica que los valores coincidan con lo esperado

        assertEquals("luis1991", outputDto.getUsuario()); // Verificar el nombre

    }

    /****************************************TESTEANDO getPersonaById**************************************************/
    @Test
    public void testGetPersonaById() {
        Integer idPersona = 1; // El ID que deseamos buscar
        Persona persona = new Persona();
        persona.setIdPersona(idPersona);
        when(personaRepository.findById(idPersona)).thenReturn(Optional.of(persona));

        // Ejecutar el método que deseas probar
        Object result = personaService.getPersonaId(idPersona, "default");
        // Verificar que el resultado no sea nulo y sea del tipo esperado (PersonaOutputDto)
        assertNotNull(result);
        assertTrue(result instanceof PersonaOutputDTO);

        PersonaOutputDTO outputDto = (PersonaOutputDTO) result;
        assertEquals(idPersona, outputDto.getIdPersona()); // Verificar el ID

    }

    @Test
    public void testGetPersonaByIdNotFound() {
        Integer idPersona = 1;
        when(personaRepository.findById(idPersona)).thenReturn(Optional.empty());

        // Ejecutar el método que deseas probar y esperar una excepción EntityNotFoundException
        assertThrows(EntityNotFoundException.class, () -> {
            personaService.getPersonaId(idPersona, "default");
        });

        // Verificar que se llamó al método findById con el ID deseado
        verify(personaRepository).findById(idPersona);
    }

    /**************************************TESTEANDO getPersonaByUsuario*************************************************/

    @Test
    public void testGetPersonaByUsuario_Student() {
        String usuario = "alumno123";
        String param = "alumno";

        // Crea un objeto Persona y configura el repositorio para devolverlo
        Persona persona = new Persona();
        persona.setUsuario(usuario);
        persona.setStudent(new Student());
        when(personaRepository.findAll()).thenReturn(List.of(persona));

        // Prueba que el método devuelve un objeto PersonaStudentDTO
        Object result = personaService.getPersonaByUsuario(usuario, param);
        assertTrue(result instanceof PersonaStudentOutputDto);

        // Verifica que el método findAll() del repositorio se haya llamado una vez
        verify(personaRepository, times(1)).findAll();

    }


    @Test
    public void testGetPersonaByUsuario_Profesor() {
        String usuario = "profesor456";
        String param = "profesor";

        // Crea un objeto Persona y configura el repositorio para devolverlo
        Persona persona = new Persona();
        persona.setUsuario(usuario);
        persona.setProfesor(new Profesor());
        when(personaRepository.findAll()).thenReturn(List.of(persona));

        // Prueba que el método devuelve un objeto PersonaProfesorDTO
        Object result = personaService.getPersonaByUsuario(usuario, param);
        assertTrue(result instanceof PersonaProfesorOutputDto);

        // Verifica que el método findAll() del repositorio se haya llamado una vez
        verify(personaRepository, times(1)).findAll();

    }

    @Test
    public void testGetPersonaByUsuario_Default() {
        String usuario = "otrousuario";
        String param = "otro"; // Valor por defecto

        // Crea un objeto Persona y configura el repositorio para devolverlo
        Persona persona = new Persona();
        persona.setUsuario(usuario);
        when(personaRepository.findAll()).thenReturn(List.of(persona));

        // Prueba que el método devuelve un objeto PersonaOutputDTO
        Object result = personaService.getPersonaByUsuario(usuario, param);
        assertTrue(result instanceof PersonaOutputDTO);

        verify(personaRepository, times(1)).findAll();


    }


    /**************************************TESTEANDO updatePersona*****************************************************/


    @Test
    public void testUpdatePersona() {
        // Configurar el comportamiento esperado del repositorio simulado
        Integer idPersona = 1; // El ID que deseamos actualizar
        PersonaInputDTO inputDTO = new PersonaInputDTO();
        inputDTO.setIdPersona(idPersona);
        inputDTO.setUsuario("luis2023"); // Nuevo valor para el apellido

        // Crear una instancia de Persona simulada con los datos iniciales
        Persona personaInicial = new Persona();
        personaInicial.setIdPersona(idPersona);
        personaInicial.setUsuario("luis1991");

        // Simular que la persona existe en el repositorio a través del id y el usuario inicial
        when(personaRepository.findById(idPersona)).thenReturn(Optional.of(personaInicial));

        // Simular el comportamiento del método save en el repositorio simulado
        // Devuelve la misma instancia con el apellido actualizado
        when(personaRepository.save(any())).thenAnswer(invocation -> {
            Persona personaGuardada = invocation.getArgument(0);
            return personaGuardada;
        });

        // Ejecutar el método que deseas probar
        PersonaOutputDTO outputDTO = personaService.updatePersona(inputDTO);

        // Verificar que se llamó al método findById con el ID deseado
        verify(personaRepository).findById(idPersona);

        // Verificar que se llamó al método save en el repositorio simulado
        verify(personaRepository).save(any());

        // Verificar que el resultado no sea nulo
        assertNotNull(outputDTO);

        // Verificar que el apellido en el resultado coincida con el valor actualizado
        assertEquals(inputDTO.getSurname(), outputDTO.getSurname());
    }


    @Test
    public void testUpdatePersonaNotFound() {
        // Configurar el comportamiento esperado del repositorio simulado
        Integer idPersona = 1; // El ID que deseamos actualizar
        PersonaInputDTO inputDTO = new PersonaInputDTO();
        inputDTO.setIdPersona(idPersona);
        inputDTO.setUsuario("luis1991"); // Nuevo valor para el usuario

        // Simular que no se encuentra ninguna persona con el ID proporcionado
        when(personaRepository.findById(idPersona)).thenReturn(Optional.empty());

        // Ejecutar el método que deseas probar y esperar que lance EntityNotFoundException
        assertThrows(EntityNotFoundException.class, () -> {
            personaService.updatePersona(inputDTO);
        });

        // Verificar que se llamó al método findById con el ID deseado
        verify(personaRepository).findById(idPersona);

        // Verificar que no se llamó al método save en el repositorio simulado
        verify(personaRepository, never()).save(any());

    }

    /**************************************TESTEANDO deletePersonaById*************************************************/

    @Test
    public void testDeletePersonaById() {
        // Configurar el comportamiento esperado del repositorio simulado
        Integer idPersona = 1; // El ID que deseamos eliminar

        // Configurar el repositorio simulado para que devuelva una Persona cuando se busque por el ID
        Persona personaMock = new Persona();
        personaMock.setIdPersona(idPersona);
        when(personaRepository.findById(idPersona)).thenReturn(Optional.of(personaMock));

        // Ejecutar el método que deseas probar
        personaService.deletePersonaById(idPersona);

        // Verificar que se llamó al método findById con el ID deseado
        verify(personaRepository).findById(idPersona);

        // Verificar que se llamó al método deleteById con el ID de la Persona encontrada
        verify(personaRepository).deleteById(idPersona);
    }

    @Test
    public void testDeletePersonaByIdNotFound(){

        //Configurar el comportamiento esperado del repositorio simulado
        Integer idPersona = 1;

        PersonaInputDTO inputDTO = new PersonaInputDTO();
        inputDTO.setIdPersona(idPersona);

        when(personaRepository.findById(idPersona)).thenReturn(Optional.empty());
        // Prueba que se lance EntityNotFoundException al intentar eliminar el ID inexistente
        assertThrows(EntityNotFoundException.class, () -> {
            personaService.deletePersonaById(idPersona);
        });
    }

    /**************************************TESTEANDO getAllPersonas*************************************************/

    @Test
    public void testGetAllPersonas() {
        // Configurar el comportamiento esperado del repositorio simulado
        int pageNumber = 0;
        int pageSize = 10;

        // Crear una lista de Personas simuladas para el contenido de la página
        List<Persona> personasSimuladas = crearListaDePersonas(); // Define tu propia lista aquí

        // Crear un objeto Page simulado
        Page<Persona> pageSimulado = new PageImpl<>(personasSimuladas);

        when(personaRepository.findAll(any(PageRequest.class))).thenReturn(pageSimulado);

        // Ejecutar el método que deseas probar
        List<PersonaOutputDTO> outputDTOs = personaService.getAllPersonas(pageNumber, pageSize);

        // Verificar que se llamó al método findAll con la configuración de PageRequest deseada
        verify(personaRepository).findAll(PageRequest.of(pageNumber, pageSize));

        // Verificar que el resultado no sea nulo y tenga la misma cantidad de elementos que la lista simulada
        assertNotNull(outputDTOs);
        assertEquals(personasSimuladas.size(), outputDTOs.size());
    }

    private List<Persona> crearListaDePersonas() {
        // Crea una lista de personas ficticia para simular el contenido de la página
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona());
        personas.add(new Persona());
        // Agrega más personas según sea necesario
        return personas;
    }

}




