package com.example.block11upload.rest;

import com.example.block11upload.entity.FileEntity;
import com.example.block11upload.rest.dto.FileOutputDto;
import com.example.block11upload.service.FileServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Slf4j
@RestController
public class FileController {

    @Autowired
    FileServiceImp fileServiceImp;

    @PostMapping("/upload/{tipo}")
    public String uploadFile(
            @RequestParam("file") MultipartFile file,
            @PathVariable String tipo,
            @RequestParam("categoria") String categoria){
        log.info("Upload File {} ", file.getOriginalFilename());
        return fileServiceImp.uploadFile(file, tipo, categoria);
    }
}
