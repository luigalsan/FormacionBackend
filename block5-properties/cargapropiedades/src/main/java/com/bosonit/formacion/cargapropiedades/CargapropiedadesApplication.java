package com.bosonit.formacion.cargapropiedades;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CargapropiedadesApplication {

	@Value("${greeting}")
	private String greeting;

	@Value("${my.number}")
	private int myNumber;

	public static void main(String[] args) {
		SpringApplication.run(CargapropiedadesApplication.class, args);
	}


	//Lo ejecuta automáticamente sin estar dentro del main
	@Bean
	public void printValues(){
		System.out.println("Greeting: " + greeting);
		System.out.println("My Number: " + myNumber);

	}


}
