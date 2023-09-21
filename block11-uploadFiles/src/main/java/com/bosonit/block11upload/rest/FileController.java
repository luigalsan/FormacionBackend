package com.bosonit.block11upload.rest;

import com.bosonit.block11upload.entity.FileEntity;
import com.bosonit.block11upload.repository.FileRepository;
import com.bosonit.block11upload.service.FileServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class FileController {

    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    FileRepository fileRepository;

    @PostMapping("/upload/{tipo}")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("categoria") String categoria,
            @PathVariable String tipo){
        log.info("Upload File {} ", file.getOriginalFilename());
        fileServiceImp.uploadFile(file, categoria, tipo);
        return ResponseEntity.ok().body("El archivo se ha subido satisfactoriamente");
    }

    @GetMapping("/setpath")
    public ResponseEntity<String> setPath(@RequestParam("path") String path){
        log.info("Setting a new path {} ", path);
        fileServiceImp.setFileUploadLocation(path);
        return ResponseEntity.ok().body("Se ha cambiado la ruta del directorio de archivos a " + path);
    }

    @GetMapping("/download/fileId")
    public ResponseEntity<String> findById(@RequestParam("id") Integer id){
        String name = fileRepository.findById(id).get().getName(); //Busco en el repositorio la
        fileServiceImp.downloadFile(name);

        return ResponseEntity.ok().body("El archivo con el ID: " + id + " se ha descargado satisfactoriamente");

    }

    @GetMapping("/download/fileName")
    public ResponseEntity<String> findByName(@RequestParam("name") String nombre) {

        FileEntity entidad = fileRepository.findAll().stream()
                .filter(persona -> persona.getName()
                        .equals(nombre)).findFirst().get();
        String name = entidad.getName();

        fileServiceImp.downloadFile(name);

        return ResponseEntity.ok().body("El archivo con nombre " + name + " se ha descargado satisfactoriamente");

    }

}
