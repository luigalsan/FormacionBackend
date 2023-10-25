package com.bosonit.cloud.backend.springcloudbackend.controller;


import com.bosonit.cloud.backend.springcloudbackend.application.ViajeServiceImpl;
import com.bosonit.cloud.backend.springcloudbackend.model.dto.ViajeInputDto;
import com.bosonit.cloud.backend.springcloudbackend.model.dto.ViajeOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaje")
public class ViajeController {

    @Autowired
    ViajeServiceImpl viajeService;

    @PostMapping
    public ResponseEntity<ViajeOutputDto> addViaje(@RequestBody ViajeInputDto viajeInputDto){
        return ResponseEntity.ok().body(viajeService.addViaje(viajeInputDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViajeOutputDto> getViajeById(@PathVariable int id){
        return ResponseEntity.ok().body(viajeService.findViajeById(id));
    }

    @GetMapping
    public ResponseEntity<List<ViajeOutputDto>> getAllViajes(){
        return ResponseEntity.ok().body(viajeService.getAllViajes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViajeOutputDto> updateViaje(@PathVariable int id, ViajeInputDto viajeInputDto){
        return ResponseEntity.ok().body(viajeService.updateViaje(id,viajeInputDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteViaje(@PathVariable int id){
        viajeService.deleteViaje(id);
        return ResponseEntity.ok().body("Viaje eliminado");
    }

    @PostMapping("/addPassenger/{idCliente}/{idViaje}")
    public ResponseEntity<String> addPasajeroToViaje(@PathVariable int idCliente, @PathVariable int idViaje){
        viajeService.addPasajeroToViaje(idCliente, idViaje);
        return ResponseEntity.ok().body("El pasajero con id : " + idCliente + " se ha añadido al viaje: " + idViaje);
    }

    @GetMapping("/passenger/count/{id}")
    public ResponseEntity<String> countPasajeros(@PathVariable int id){
        return ResponseEntity.ok().body("Total pasajeros: " + viajeService.countPasajeros(id));
    }

    @PutMapping("/{id}/{tripStatus}")
    public ResponseEntity<String> updateStatus(@PathVariable int id, @PathVariable boolean tripStatus){
        viajeService.updateStatusBus(id,tripStatus);
        String estado;
        if(tripStatus){
            estado = "Disponible";
        }else {
            estado = "No disponible";
        }
        return ResponseEntity.ok().body("Estado del bus: " + estado);
    }

    @GetMapping("/verify/{id}")
    public ResponseEntity<String> getStatus(@PathVariable int id){
        String estado;
        if(viajeService.getStatusBus(id)){
            estado = "Disponible";
        }else {
            estado = "No disponible";
        }
        return ResponseEntity.ok().body("Estado del bus: " + estado);
    }

}
