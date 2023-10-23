package com.bosonit.cloud.backend.springcloudbackend.controller;


import com.bosonit.cloud.backend.springcloudbackend.application.ViajeService;
import com.bosonit.cloud.backend.springcloudbackend.application.ViajeServiceImpl;
import com.bosonit.cloud.backend.springcloudbackend.model.dto.ViajeInputDto;
import com.bosonit.cloud.backend.springcloudbackend.model.dto.ViajeOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viaje")
public class ViajeController {

    @Autowired
    ViajeServiceImpl viajeService;

    @PostMapping
    public ResponseEntity<ViajeOutputDto> viajeOutputDto(@RequestBody ViajeInputDto viajeInputDto){
        return ResponseEntity.ok().body(viajeService.addViaje(viajeInputDto));

    }

    @GetMapping("/hello")
    public String hello(){
        return "hola";
    }

}
