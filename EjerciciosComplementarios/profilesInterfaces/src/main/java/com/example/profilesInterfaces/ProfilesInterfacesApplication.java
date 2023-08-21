package com.example.profilesInterfaces;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProfilesInterfacesApplication {

	private final Mensaje mensaje;
	@Autowired

	public ProfilesInterfacesApplication(Mensaje mensaje){

		this.mensaje = mensaje;
	}

	@PostConstruct
	public void printMessage(){
		String mensajeVar = mensaje.getMessage(); //Se podría hacer desde el constructor de la clase actual, pero mejor hacerlo así
		System.out.println(mensajeVar);
	}

	public static void main(String[] args) {
		SpringApplication.run(ProfilesInterfacesApplication.class, args);
	}

}
