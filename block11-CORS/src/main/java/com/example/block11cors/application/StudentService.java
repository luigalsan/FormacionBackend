package com.example.block11cors.application;

import com.example.block11cors.controller.dto.Asignatura.AsignaturaOutputDTO;
import com.example.block11cors.controller.dto.Student.StudentInputDto;
import com.example.block11cors.controller.dto.Student.StudentOutputDtoFull;
import com.example.block11cors.controller.dto.Student.StudentOutputDtoSimple;

import java.util.List;

public interface StudentService {
    StudentOutputDtoFull addStudent(StudentInputDto studentInputDTO) throws Exception;
    StudentOutputDtoSimple getStudentByIdSimple(Integer id);
    StudentOutputDtoFull getStudentByIdFull(Integer id);
    List<AsignaturaOutputDTO> getAsignaturasByIdStudent(Integer id);
    Iterable<StudentOutputDtoSimple> getAllStudents(int pageNumber, int pageSize);
    StudentOutputDtoSimple updateStudent(StudentInputDto studentInputDTO);
    void deleteStudentById(Integer id);
    void addAsignaturaToStudent(Integer id_asignatura, Integer id_student);
    void asignarAsignaturasEstudiante(Integer id_student, List<Integer> asignaturasId);
    void desasignarAsignaturasEstudiante(Integer id_student, List<Integer> asignaturasId);
}
