package com.bosonit.blockcrudvalidation.application;


import com.bosonit.blockcrudvalidation.controller.dto.Asignatura.AsignaturaInputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Asignatura.AsignaturaOutputDTO;

import java.util.List;

public interface AsignaturaService {
    AsignaturaOutputDTO addAsignatura(AsignaturaInputDTO asignaturaInputDTO);
    AsignaturaOutputDTO getAsignaturaById(Integer id);
    Iterable<AsignaturaOutputDTO> getAllAsignaturas(Integer pageNumber, Integer pageSize);
    List<AsignaturaOutputDTO> getAsignaturaByStudent(Integer Id);
    AsignaturaOutputDTO updateAsignatura(AsignaturaInputDTO asignaturaInputDTO);
    void deleteAsignaturaById(Integer id);

}
