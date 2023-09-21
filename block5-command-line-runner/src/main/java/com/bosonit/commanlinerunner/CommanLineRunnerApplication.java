package com.bosonit.commanlinerunner;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommanLineRunnerApplication{


	Terciaria clase3;
	Secundaria clase2;

	@Autowired
	public CommanLineRunnerApplication(Secundaria clase2, Terciaria clase3){
		this.clase2 = clase2;
		System.out.println(clase2.segundaFuncion());
		System.out.println(clase3.terceraFuncion());
	}

	@PostConstruct //Se ejecutará una vez que se hayan construido el bean y todas las dependencias hayan sido inyectadas
	public void primeraFuncion(){
		System.out.println("Hola desde la clase inicial");
	}



	public static void main(String[] args) {
		SpringApplication.run(CommanLineRunnerApplication.class, "Esto es un valor pasado por parámetro");
	}

}
