package com.example.block11upload.rest;

import com.example.block11upload.entity.FileEntity;
import com.example.block11upload.rest.dto.FileOutputDto;
import com.example.block11upload.service.FileServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
            @RequestParam("categoria") String categoria,
            @PathVariable String tipo){
        log.info("Upload File {} ", file.getOriginalFilename());
        return fileServiceImp.uploadFile(file, categoria, tipo);
    }

    @GetMapping("/setpath")
    public String setPath(
            @RequestParam("path") String path){
        log.info("Setting a new path {} ", path);
        fileServiceImp.setFileUploadLocation(path);
        return "Se ha cambiado la ruta del directorio de archivos a " + path;

    }

}
