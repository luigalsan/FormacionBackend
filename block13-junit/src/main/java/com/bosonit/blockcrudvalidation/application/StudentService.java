package com.bosonit.blockcrudvalidation.application;

import com.bosonit.blockcrudvalidation.controller.dto.Asignatura.AsignaturaOutputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Student.StudentInputDto;
import com.bosonit.blockcrudvalidation.controller.dto.Student.StudentOutputDtoFull;
import com.bosonit.blockcrudvalidation.controller.dto.Student.StudentOutputDtoSimple;
import com.bosonit.blockcrudvalidation.error.UnprocessableEntityException;

import java.util.List;

public interface StudentService {
    StudentOutputDtoFull addStudent(StudentInputDto studentInputDTO) throws UnprocessableEntityException;
    StudentOutputDtoSimple getStudentByIdSimple(Integer id);
    StudentOutputDtoFull getStudentByIdFull(Integer id);
    List<AsignaturaOutputDTO> getAsignaturasByIdStudent(Integer id);
    Iterable<StudentOutputDtoSimple> getAllStudents(int pageNumber, int pageSize);
    StudentOutputDtoSimple updateStudent(StudentInputDto studentInputDTO);
    void deleteStudentById(Integer id);
    void addAsignaturaToStudent(Integer idAsignatura, Integer idStudent);
    void asignarAsignaturasEstudiante(Integer idStudent, List<Integer> asignaturasId);
    void desasignarAsignaturasEstudiante(Integer idStudent, List<Integer> asignaturasId);
}
