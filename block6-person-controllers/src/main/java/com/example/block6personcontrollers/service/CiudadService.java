package com.example.block6personcontrollers.service;

import com.example.block6personcontrollers.entity.Ciudad;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

@Service
public class CiudadService {

    private List<Ciudad> ciudades = new ArrayList<>();
    public void addCiudad(Ciudad ciudad){
        ciudades.add(ciudad);

    }
    public List<Ciudad> getCiudades() {
        return ciudades;
    }
}
