package com.example.block11cors.application;


import com.example.block11cors.controller.dto.Asignatura.AsignaturaInputDTO;
import com.example.block11cors.controller.dto.Asignatura.AsignaturaOutputDTO;

import java.util.List;

public interface AsignaturaService {
    AsignaturaOutputDTO addAsignatura(AsignaturaInputDTO asignaturaInputDTO);
    AsignaturaOutputDTO getAsignaturaById(Integer id);
    Iterable<AsignaturaOutputDTO> getAllAsignaturas(Integer pageNumber, Integer pageSize);
    List<AsignaturaOutputDTO> getAsignaturaByStudent(Integer Id);
    AsignaturaOutputDTO updateAsignatura(AsignaturaInputDTO asignaturaInputDTO);
    void deleteAsignaturaById(Integer id);

}
