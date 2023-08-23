package com.example.block6pathvariableheaders.rest;


import com.example.block6pathvariableheaders.entity.Entity;
import com.example.block6pathvariableheaders.entity.ObjetoTipo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {
    @PostMapping("/body")
    public Entity getBody(@RequestBody Entity respuesta ){

        return new Entity(respuesta.getId(), respuesta.getContent());
    }
    @GetMapping("/user/{id}")
    public int getId(@RequestParam int id){
        return id;
    }

    @PutMapping("/put")
    public ResponseEntity<HashMap<String, String>> getHashmap(@RequestParam HashMap<String, String> requestParam){

        return ResponseEntity.ok(requestParam);
    }

    @GetMapping("/header")
    public ResponseEntity<String> getHeaders (@RequestHeader(name = "h1", required = false) String h1Valor,
        @RequestHeader(name = "h2", required = false) String h2Valor) {

        String response = "El valor del parámetro h1 es: " + h1Valor + "\nEl valor del parámetro H2 es: " + h2Valor;

        return ResponseEntity.ok(response);
    }

    @PostMapping("/all")
    public ResponseEntity<ObjetoTipo> getAll(@RequestBody(required=false) String body, //Es opcional
                                             @RequestHeader Map<String, String> headers, //Si no se manda aparecerá excepción
                                             @RequestParam Map<String, String> requestParam //Si no se manda, aparecerá excepción
                                             ){

        List<String> listHeaders = new ArrayList<>(headers.values());
        List<String> listParam = new ArrayList<>(requestParam.values());

        ObjetoTipo tipo = new ObjetoTipo(body, listHeaders, listParam);

        return ResponseEntity.ok(tipo);

    }


}

