package com.bosonit.block11upload.entity;

import com.bosonit.block11upload.rest.dto.FileInputDto;
import com.bosonit.block11upload.rest.dto.FileOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "file")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Date uploadDate;

    public FileEntity(FileInputDto fileInputDto){
        this.id = fileInputDto.getId();
        this.name = fileInputDto.getName();
        this.category = fileInputDto.getCategory();
        this.uploadDate = fileInputDto.getUploadDate();
    }

    public FileOutputDto fileOutputDto(){
        return new FileOutputDto(
                this.id,
                this.name,
                this.category,
                this.uploadDate
        );
    }

}
