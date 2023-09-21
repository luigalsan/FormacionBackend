package com.bosonit.block11upload.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileOutputDto {
    private Integer id;
    private String name;
    private String category;
    private Date uploadDate;
}
