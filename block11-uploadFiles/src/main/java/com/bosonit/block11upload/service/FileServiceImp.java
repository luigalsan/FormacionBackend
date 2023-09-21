package com.bosonit.block11upload.service;

import com.bosonit.block11upload.entity.FileEntity;
import com.bosonit.block11upload.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;


@Slf4j
@Service
public class FileServiceImp implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Value(("${cod.file.storage.location}")) //Esta línea apunta al properties donde tengo definida la localizacion del fichero
    private String fileLocation;

public String uploadFile(MultipartFile file, String categoria, String tipo){
    String messageResponse = null;

    String originalFilename = file.getOriginalFilename();
    // Extraer la extensión del nombre del archivo
    String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

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
        Path path = Paths.get(fileLocation + originalFilename);
        Files.write(path, bytes);

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

public void setFileUploadLocation(String fileUploadLocation){
    this.fileLocation = fileUploadLocation;
}

public InputStreamResource downloadFile(String fileName){

    log.info("Downloading file {}", fileName);
    InputStreamResource resource = null;
    try {
        File file = new File(fileLocation + fileName);
        resource = new InputStreamResource(new FileInputStream(file));
    }catch (FileNotFoundException e){
        e.printStackTrace();
    }

        return resource;
    }

public InputStreamResource findById(Integer id){
    String name = fileRepository.findById(id).get().getName(); //Busco en el repositorio la

    return downloadFile(name);

}

public InputStreamResource findByName(String name){
    FileEntity entidad = fileRepository.findAll().stream()
            .filter(persona -> persona.getName()
                    .equals(name)).findFirst().get();

    return downloadFile(entidad.getName());
}


}







