package com.bosonit.block7crudvalidation.service;

import com.bosonit.block7crudvalidation.application.impl.AsignaturaServiceImpl;
import com.bosonit.block7crudvalidation.controller.dto.Asignatura.AsignaturaInputDTO;
import com.bosonit.block7crudvalidation.controller.dto.Asignatura.AsignaturaOutputDTO;
import com.bosonit.block7crudvalidation.entity.Asignatura;
import com.bosonit.block7crudvalidation.error.EntityNotFoundException;
import com.bosonit.block7crudvalidation.repository.AsignaturaRepository;
import com.bosonit.block7crudvalidation.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AsignaturaServiceTest {

    @InjectMocks
    private AsignaturaServiceImpl asignaturaService;

    @Mock
    private AsignaturaRepository asignaturaRepository;

    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**************************************** TESTEANDO addAsignatura ******************************************************/

    @Test
    public void testAddAsignatura() {

        AsignaturaInputDTO inputDto = new AsignaturaInputDTO();
        inputDto.setAsignatura("Informática");
        inputDto.setComment("Comentario");

        Asignatura asignatura = new Asignatura();

        asignatura.setAsignatura("Informática");
        asignatura.setComments("Comentario");

        when(asignaturaRepository.save(any())).thenReturn(asignatura);
        AsignaturaOutputDTO outputDto = asignaturaService.addAsignatura(inputDto);

        assertEquals("Informática", outputDto.getAsignatura());
        assertEquals("Comentario", outputDto.getComment());
    }

    /****************************************TESTEANDO getAsignaturaById**************************************************/
    @Test
    public void testGetAsignaturaById() {
        Integer idAsignatura = 1;
        Asignatura asignatura = new Asignatura();
        asignatura.setId_asignatura(idAsignatura);
        when(asignaturaRepository.findById(idAsignatura)).thenReturn(Optional.of(asignatura));

        Object result = asignaturaService.getAsignaturaById(idAsignatura);

        assertNotNull(result);
        assertTrue(result instanceof AsignaturaOutputDTO);

        AsignaturaOutputDTO outputDto = (AsignaturaOutputDTO) result;
        assertEquals(idAsignatura, outputDto.getId_asignatura()); // Verificar el ID

    }

    @Test
    public void testGetAsignaturaByIdNotFound() {
        Integer idAsignatura = 1;
        when(asignaturaRepository.findById(idAsignatura)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            asignaturaService.getAsignaturaById(idAsignatura);
        });

        verify(asignaturaRepository).findById(idAsignatura);
    }


    /**************************************** TESTEANDO getAllAsignaturas ************************************************/

    @Test
    public void testGetAllAsignaturas() {
        int pageNumber = 0;
        int pageSize = 10;

        List<Asignatura> asignaturasSimuladas = crearListaDeAsignaturas(); // Define tu propia lista aquí

        Page<Asignatura> pageSimulado = new PageImpl<>(asignaturasSimuladas);

        when(asignaturaRepository.findAll(any(PageRequest.class))).thenReturn(pageSimulado);

        List<AsignaturaOutputDTO> outputDTOs = (List<AsignaturaOutputDTO>) asignaturaService.getAllAsignaturas(pageNumber, pageSize);

        verify(asignaturaRepository).findAll(PageRequest.of(pageNumber, pageSize));

        assertNotNull(outputDTOs);
        assertEquals(asignaturasSimuladas.size(), outputDTOs.size());
    }

    private List<Asignatura> crearListaDeAsignaturas() {
        List<Asignatura> asignaturas = new ArrayList<>();
        asignaturas.add(new Asignatura());
        asignaturas.add(new Asignatura());
        return asignaturas;
    }

    /**************************************** TESTEANDO updateAsignatura ************************************************/

    @Test
    public void testUpdateAsignatura() {
        Integer idAsignatura = 1;
        AsignaturaInputDTO inputDTO = new AsignaturaInputDTO();
        inputDTO.setId_asignatura(idAsignatura);
        inputDTO.setAsignatura("Biología");
        inputDTO.setComment("Comentario");

        Asignatura asignatura = new Asignatura();
        asignatura.setId_asignatura(idAsignatura);
        asignatura.setAsignatura("Biología");
        asignatura.setComments("Comentario");

        when(asignaturaRepository.findById(idAsignatura)).thenReturn(Optional.of(asignatura));
        when(asignaturaRepository.save(any())).thenAnswer(invocation -> {
            Asignatura asignaturaGuardada = invocation.getArgument(0);
            return asignaturaGuardada;
        });

        AsignaturaOutputDTO outputDTO = asignaturaService.updateAsignatura(inputDTO);

        verify(asignaturaRepository).findById(idAsignatura);
        verify(asignaturaRepository).save(any());

        assertEquals(inputDTO.getAsignatura(), outputDTO.getAsignatura());
    }


    @Test
    public void testUpdateAsignaturaNotFound() {
        Integer idAsignatura = 1;
        AsignaturaInputDTO inputDTO = new AsignaturaInputDTO();
        inputDTO.setId_asignatura(idAsignatura);
        inputDTO.setAsignatura("Biología");
        inputDTO.setComment("Comentario");


        when(asignaturaRepository.findById(idAsignatura)).thenReturn(Optional.empty());


        assertThrows(EntityNotFoundException.class, () -> {
            asignaturaService.updateAsignatura(inputDTO);
        });

        verify(asignaturaRepository).findById(idAsignatura);
        verify(asignaturaRepository, never()).save(any());

    }

    /**************************************TESTEANDO deleteAsignaturaById*************************************************/

    @Test
    public void testDeleteAsignaturaById() {

        Integer idAsignatura = 1;

        Asignatura asignatura = new Asignatura();
        asignatura.setId_asignatura(idAsignatura);
        when(asignaturaRepository.findById(idAsignatura)).thenReturn(Optional.of(asignatura));

        asignaturaService.deleteAsignaturaById(idAsignatura);

        verify(asignaturaRepository).findById(idAsignatura);
        verify(asignaturaRepository).deleteById(idAsignatura);
    }

    @Test
    public void testDeleteAsignaturaByIdNotFound(){

        Integer idAsignatura = 1;
        AsignaturaInputDTO inputDTO = new AsignaturaInputDTO();
        inputDTO.setId_asignatura(idAsignatura);

        when(asignaturaRepository.findById(idAsignatura)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> {
            asignaturaService.deleteAsignaturaById(idAsignatura);
        });
    }

}
