package com.bosonit.formacion.cargapropiedades;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class CargapropiedadesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CargapropiedadesApplication.class, args);

	}

}

@Component
class ejecutarVariables implements CommandLineRunner{

	@Value("${greeting}")
	private String greeting;

	@Value("${my.number}")
	private int myNumber;

	@Value("${new.property}")
	private String newProperty;

	@Value("${MYURL}")
	private String myURL;

	@Value("${MYURL2:NO TENGO VALOR}")
	private String myURL2;


	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void run(String... args) throws Exception {
		logger.info("Valor de greeting", greeting);
		logger.info("Valor de mi numero", myNumber);
		logger.info("Valor de newProperty", newProperty);
		logger.info("Valor de MYURL", myURL);
		logger.info("Valor de MYURL2", myURL2);

	}

}


