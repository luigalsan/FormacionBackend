package com.example.creandoLogs;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@Slf4j
public class CreandoLogsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreandoLogsApplication.class, args);
	}


}
