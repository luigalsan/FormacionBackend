package com.example.block11upload.service;

import com.example.block11upload.entity.FileEntity;
import com.example.block11upload.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;


@Slf4j
@Service
public class FileServiceImp {

    @Autowired
    private FileRepository fileRepository;

    @Value(("${cod.file.storage.location}")) //Esta línea apunta al properties donde tengo definida la localizacion del fichero
    private String fileUploadLocation;

    @Value("${download.location}")
    private String fileDownloadLocation;

public String uploadFile(MultipartFile file, String categoria, String tipo){
    String messageResponse = null;

    String originalFilename = file.getOriginalFilename();
    // Extraer la extensión del nombre del archivo
    String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    log.info("La extensión del archivo es: " + fileExtension
    + "/El tipo del archivo es: " + tipo);

    if(!fileExtension.equals(tipo)){
        messageResponse = "El archivo a subir no coincide con el tipo";
        return messageResponse;
    }

    if (file.isEmpty()) {
        messageResponse = "Por favor, selecciona un tipo valido a subir";
        return messageResponse;
    }

    log.info("Upload File {} ", file.getOriginalFilename());

    try {
        //Obtener el file y guardarlo en algún lugar
        byte[] bytes = file.getBytes();
        Path path = Paths.get(fileUploadLocation + originalFilename);
        Files.write(path, bytes);

        messageResponse = "You successfully uploaded " + originalFilename + "";

        FileEntity fichero = new FileEntity();
        fichero.setName(originalFilename);
        fichero.setCategory(categoria);
        fichero.setUploadDate(new Date());

        fileRepository.save(fichero).fileOutputDto();

    }catch(IOException e){
        e.printStackTrace();
    }

    return messageResponse;
}

}





