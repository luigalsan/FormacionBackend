package com.example.block7crudvalidation.application.impl;

import com.example.block7crudvalidation.application.AsignaturaService;
import com.example.block7crudvalidation.controller.dto.Asignatura.AsignaturaInputDTO;
import com.example.block7crudvalidation.controller.dto.Asignatura.AsignaturaOutputDTO;
import com.example.block7crudvalidation.controller.dto.Student.StudentInputDto;
import com.example.block7crudvalidation.entity.Asignatura;
import com.example.block7crudvalidation.entity.Student;
import com.example.block7crudvalidation.error.EntityNotFoundException;
import com.example.block7crudvalidation.error.UnprocessableEntityException;
import com.example.block7crudvalidation.repository.AsignaturaRepository;
import com.example.block7crudvalidation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaServiceImpl implements AsignaturaService{

    @Autowired
    AsignaturaRepository asignaturaRepository;

    @Autowired
    StudentRepository studentRepository;

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
    public List<AsignaturaOutputDTO> getAsignaturaByStudent(Integer id) {
        //Creo instancia de student que coincida por el id para luego mediante stream obtener todas las asignaturas
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el estudiante con id " + id));

        return student.getAsignatura().stream().map(Asignatura::asignaturaToOutputDto).toList();
    }

    @Override
    public AsignaturaOutputDTO updateAsignatura(AsignaturaInputDTO asignaturaInputDTO) {
        Asignatura asignatura = asignaturaRepository.findById(asignaturaInputDTO.getId_asignatura())
                .orElseThrow(() -> new EntityNotFoundException("La asignatura con id " + asignaturaInputDTO.getId_asignatura() + " no ha sido encontrada"));

        asignatura.setAsignatura(asignaturaInputDTO.getAsignatura());
        asignatura.setComments(asignatura.getComments());
        asignatura.setInitial_date(asignatura.getInitial_date());
        asignatura.setFinish_date(asignatura.getFinish_date());

        return asignaturaRepository.save(asignatura).asignaturaToOutputDto();
    }


    @Override
    public void deleteAsignaturaById(Integer id) {
        asignaturaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encuentra la asignatura con el id " + id));
        asignaturaRepository.deleteById(id);
    }

}
