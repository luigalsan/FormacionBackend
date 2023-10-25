package com.bosonit.cloud.backend.springcloudbackend;

import com.netflix.discovery.EurekaNamespace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class SpringCloudBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudBackendApplication.class, args);
	}

}
