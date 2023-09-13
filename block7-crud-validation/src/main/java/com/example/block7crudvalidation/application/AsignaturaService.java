package com.example.block7crudvalidation.application;


import com.example.block7crudvalidation.controller.dto.Asignatura.AsignaturaInputDTO;
import com.example.block7crudvalidation.controller.dto.Asignatura.AsignaturaOutputDTO;
import com.example.block7crudvalidation.controller.dto.Student.StudentInputDto;

import java.util.List;

public interface AsignaturaService {
    AsignaturaOutputDTO addAsignatura(AsignaturaInputDTO asignaturaInputDTO);
    AsignaturaOutputDTO getAsignaturaById(Integer id);
    Iterable<AsignaturaOutputDTO> getAllAsignaturas(Integer pageNumber, Integer pageSize);
    List<AsignaturaOutputDTO> getAsignaturaByStudent(Integer Id);
    AsignaturaOutputDTO updateAsignatura(AsignaturaInputDTO asignaturaInputDTO);
    void deleteAsignaturaById(Integer id);

}
