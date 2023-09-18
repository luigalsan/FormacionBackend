package com.example.block11cors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class Block11Cors {

	public static void main(String[] args) {
		SpringApplication.run(Block11Cors.class, args);
	}

}
