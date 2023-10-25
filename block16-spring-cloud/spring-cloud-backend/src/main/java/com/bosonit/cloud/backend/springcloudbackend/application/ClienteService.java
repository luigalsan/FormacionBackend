package com.bosonit.cloud.backend.springcloudbackend.application;

import com.bosonit.cloud.backend.springcloudbackend.model.dto.ClienteInputDto;
import com.bosonit.cloud.backend.springcloudbackend.model.dto.ClienteOutputDto;

import java.util.List;

public interface ClienteService {
    ClienteOutputDto createCliente(ClienteInputDto clienteInputDto);
    ClienteOutputDto findClienteById(int id);
    ClienteOutputDto updateCliente(int id, ClienteInputDto clienteInputDto);
    void deleteClienteById(int id);
    public List<ClienteOutputDto> getAllClientes();
}
