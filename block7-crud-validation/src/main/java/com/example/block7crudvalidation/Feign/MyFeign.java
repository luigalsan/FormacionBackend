package com.example.block7crudvalidation.Feign;


import com.example.block7crudvalidation.controller.dto.Profesor.ProfesorOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8081", name = "profesor") //En VM options en el Run Configuration poner: -Dserver.port=8081
public interface MyFeign {
    @GetMapping("/profesor/{id}")
    ProfesorOutputDTO getProfesorById(@PathVariable Integer id);

}