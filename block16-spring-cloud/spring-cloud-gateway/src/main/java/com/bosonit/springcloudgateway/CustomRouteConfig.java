package com.bosonit.springcloudgateway;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("api-backend", r -> r
                        .path("/cliente/**")
                        .uri("http://localhost:8081/cliente"))
                .route("api-frontend", r -> r
                        .path("/viaje/**")
                        .uri("http://localhost:8081/viaje"))
                .route("generateTicket_route", r -> r
                        .path("/generateTicket/{idCliente}/{idViaje}")
                        .filters(f -> f.rewritePath("/generateTicket/(?<idCliente>.*)/(?<idViaje>.*)", "/generateTicket/${idCliente}/${idViaje}"))
                        .uri("http://localhost:8082"))
                .build();

    }
}