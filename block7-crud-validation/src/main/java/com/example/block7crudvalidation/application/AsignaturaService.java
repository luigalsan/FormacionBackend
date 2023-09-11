package com.example.block7crudvalidation.application;


import com.example.block7crudvalidation.controller.dto.Asignatura.AsignaturaInputDTO;
import com.example.block7crudvalidation.controller.dto.Asignatura.AsignaturaOutputDTO;

public interface AsignaturaService {

    AsignaturaOutputDTO addAsignatura(AsignaturaInputDTO asignaturaInputDTO);
    AsignaturaOutputDTO getAsignaturaById(Integer id);
    Iterable<AsignaturaOutputDTO> getAllAsignaturas(Integer pageNumber, Integer pageSize);
    void deleteAsignaturaById(Integer id);

}
