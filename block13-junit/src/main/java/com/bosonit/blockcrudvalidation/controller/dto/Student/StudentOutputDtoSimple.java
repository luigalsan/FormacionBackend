package com.bosonit.blockcrudvalidation.controller.dto.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentOutputDtoSimple {

    private Integer idStudent;
    private int numHoursWeek;
    private String comments;
    private String branch;
    private Integer idPersona;

}
