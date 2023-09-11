package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.Asignatura.AsignaturaInputDTO;
import com.example.block7crudvalidation.controller.dto.Asignatura.AsignaturaOutputDTO;
import com.example.block7crudvalidation.controller.dto.Student.StudentOutputDtoFull;
import com.example.block7crudvalidation.controller.dto.Student.StudentOutputDtoSimple;

import java.util.List;

public interface StudentService {
    StudentOutputDtoFull addStudent(StudentOutputDtoSimple studentInputDTO) throws Exception;
    StudentOutputDtoSimple getStudentByIdSimple(Integer id);
    StudentOutputDtoFull getStudentByIdFull(Integer id);
    List<AsignaturaOutputDTO> getAsignaturasByIdStudent(Integer id);
    Iterable<StudentOutputDtoSimple> getAllStudents(int pageNumber, int pageSize);
    StudentOutputDtoSimple updateStudent(StudentOutputDtoSimple studentInputDTO);
    void deleteStudentById(Integer id);
}
