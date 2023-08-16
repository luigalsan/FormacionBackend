package com.bosonit.formacion.cargapropiedades;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class CargapropiedadesApplication {

	@Value("${greeting}")
	private String greeting;

	@Value("${my.number}")
	private int myNumber;

	@Value("${new.property}")
	private String newProperty;

	public static void main(String[] args) {
		SpringApplication.run(CargapropiedadesApplication.class, args);

	}


	//Lo ejecuta automáticamente sin estar dentro del main
	@Bean
	public void printValues(){
		System.out.println("El valor de greeting es: " + greeting);
		System.out.println("El valor de my.number es: " + myNumber);
		System.out.println("El valor de new.property es: " + newProperty);

		//Acceder a la variable de entorno en mi S.O.

		String myVariableSO= System.getenv("new.property");
		System.out.println(myVariableSO);

	}

}

@Component
class ejecutarDesdeCommandLine implements CommandLineRunner{

	public void run(String... run) {
		System.out.println("Imprimiendo desde el CommandLine Runner");

		String MYURL = System.getenv("MYURL");
		System.out.println(MYURL);

		String MYURL2 = System.getenv("MYURL2");
		if(MYURL2.isEmpty() || MYURL2 == null) {
			if (MYURL2 == null || MYURL2.isEmpty()) {
				MYURL2 = "NO_tengo_valor";
			}
		}

		System.out.println("El valor de MYURL2 es: " + MYURL2);



	}

}

