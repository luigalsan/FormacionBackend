package com.example.block7crudvalidation.controller.dto.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentOutputDtoSimple {

    private Integer id_student;
    private int num_hours_week;
    private String comments;
    private String branch;


}
