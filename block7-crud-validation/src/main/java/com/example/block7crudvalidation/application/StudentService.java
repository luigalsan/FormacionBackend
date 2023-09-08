package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.Student.StudentInputDTO;
import com.example.block7crudvalidation.controller.dto.Student.StudentOutputDtoFull;
import com.example.block7crudvalidation.controller.dto.Student.StudentOutputDtoSimple;

public interface StudentService {
    StudentOutputDtoFull addStudent(StudentInputDTO studentInputDTO) throws Exception;
    StudentOutputDtoSimple getStudentByIdSimple(Integer id);
    StudentOutputDtoFull getStudentByIdFull(Integer id);
    StudentOutputDtoSimple updateStudent(StudentInputDTO studentInputDTO);
    void deleteStudentById(Integer id);
}
