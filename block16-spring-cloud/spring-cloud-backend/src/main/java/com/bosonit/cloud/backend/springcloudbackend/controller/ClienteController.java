package com.bosonit.cloud.backend.springcloudbackend.controller;

import com.bosonit.cloud.backend.springcloudbackend.application.ClienteServiceImpl;
import com.bosonit.cloud.backend.springcloudbackend.model.dto.ClienteInputDto;
import com.bosonit.cloud.backend.springcloudbackend.model.dto.ClienteOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteServiceImpl clienteService;

    @PostMapping
    public ResponseEntity<ClienteOutputDto> addCliente(@RequestBody ClienteInputDto clienteInputDto){
        return ResponseEntity.ok().body(clienteService.createCliente(clienteInputDto));

    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteOutputDto> getClienteById(@PathVariable int id){
        return ResponseEntity.ok().body(clienteService.findClienteById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClienteOutputDto>> getAllClientes(){
        return ResponseEntity.ok().body(clienteService.getAllClientes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteOutputDto> updateClienteById(@PathVariable int id, @RequestBody ClienteInputDto clienteInputDto){
        return ResponseEntity.ok().body(clienteService.updateCliente(id, clienteInputDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClienteById(@PathVariable int id){
        clienteService.deleteClienteById(id);
        return ResponseEntity.ok().body("El cliente se ha eliminado correctamente");
    }

}
