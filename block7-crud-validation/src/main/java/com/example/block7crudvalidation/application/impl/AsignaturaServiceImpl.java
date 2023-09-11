package com.example.block7crudvalidation.application.impl;

import com.example.block7crudvalidation.application.AsignaturaService;
import com.example.block7crudvalidation.controller.dto.Asignatura.AsignaturaInputDTO;
import com.example.block7crudvalidation.controller.dto.Asignatura.AsignaturaOutputDTO;
import com.example.block7crudvalidation.entity.Asignatura;
import com.example.block7crudvalidation.error.EntityNotFoundException;
import com.example.block7crudvalidation.error.UnprocessableEntityException;
import com.example.block7crudvalidation.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AsignaturaServiceImpl implements AsignaturaService{

    @Autowired
    AsignaturaRepository asignaturaRepository;

    @Override
    public AsignaturaOutputDTO addAsignatura(AsignaturaInputDTO asignaturaInputDTO) {

        if(asignaturaInputDTO.getAsignatura() == null)
            throw new UnprocessableEntityException("Inserte la asignatura, por favor");
        else if(asignaturaInputDTO.getComment() == null)
            throw new UnprocessableEntityException("Inserte comentario, por favor");
        else if(asignaturaInputDTO.getInitial_date() == null)
            throw new UnprocessableEntityException("Inserte fecha inicial, por favor");
        else if(asignaturaInputDTO.getFinish_date() == null)
            throw new UnprocessableEntityException("Inserte fecha final, por favor");

        boolean existe = asignaturaRepository.findById(asignaturaInputDTO.getId_asignatura()).isPresent();

        if(existe){
            throw new UnprocessableEntityException("La asignatura ya existe");
        }else{
           return  asignaturaRepository.save(new Asignatura(asignaturaInputDTO)).asignaturaToOutputDto();
        }
    }
    @Override
    public AsignaturaOutputDTO getAsignaturaById(Integer id) {
        return asignaturaRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("No se encuentra el Id de la asignatura")).asignaturaToOutputDto();
    }

    @Override
    public Iterable<AsignaturaOutputDTO> getAllAsignaturas(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return asignaturaRepository.findAll(pageRequest).getContent()
                .stream().map(Asignatura::asignaturaToOutputDto).toList();
    }

    @Override
    public void deleteAsignaturaById(Integer id) {
        asignaturaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encuentra la asignatura con el id " + id));
        asignaturaRepository.deleteById(id);
    }

}
