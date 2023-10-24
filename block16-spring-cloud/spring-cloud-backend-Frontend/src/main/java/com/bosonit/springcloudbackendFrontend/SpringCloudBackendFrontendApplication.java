package com.bosonit.springcloudbackendFrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringCloudBackendFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudBackendFrontendApplication.class, args);
	}

}
