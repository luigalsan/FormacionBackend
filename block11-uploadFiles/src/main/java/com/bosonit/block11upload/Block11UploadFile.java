package com.bosonit.block11upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Block11UploadFile {

	public static void main(String[] args) {
		if (args.length > 0) {
			// Asigna el primer argumento como la ubicación del directorio de almacenamiento
			System.setProperty("cod.file.storage.location", args[0]);
		}
		SpringApplication.run(Block11UploadFile.class, args);
	}

}
