package com.bosonit.cloud.backend.springcloudbackend.application;


import com.bosonit.cloud.backend.springcloudbackend.error.EntityNotFoundException;
import com.bosonit.cloud.backend.springcloudbackend.model.Cliente;
import com.bosonit.cloud.backend.springcloudbackend.model.dto.ClienteInputDto;
import com.bosonit.cloud.backend.springcloudbackend.model.dto.ClienteOutputDto;
import com.bosonit.cloud.backend.springcloudbackend.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    @Transactional
    public ClienteOutputDto createCliente(ClienteInputDto clienteInputDto) {
        return clienteRepository.save(new Cliente(clienteInputDto)).clienteInputDtoToOutputDto();
    }

    @Override
    @Transactional
    public ClienteOutputDto findClienteById(int id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("La id no existe")
        ).clienteInputDtoToOutputDto();
    }

    @Override
    @Transactional
    public ClienteOutputDto updateCliente(int id, ClienteInputDto clienteInputDto) {
        // Comprobamos si el cliente existe
        Cliente cliente = clienteRepository.findById(clienteInputDto.getId()).orElseThrow(
                () -> new EntityNotFoundException("La id no existe para este cliente")
        );

        // Se modifican/actualizan los atributos del cliente
        cliente.setNombre(cliente.getNombre());
        cliente.setApellido(cliente.getApellido());
        cliente.setEmail(cliente.getEmail());
        cliente.setTelefono(cliente.getTelefono());

        // Se guarda el cliente actualizado
        return clienteRepository.save(cliente).clienteInputDtoToOutputDto();

    }

    @Override
    @Transactional
    public void deleteClienteById(int id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No existe la id")
        );

        clienteRepository.delete(cliente);
    }

    @Override
    @Transactional
    public List<ClienteOutputDto> getAllClientes() {
        return clienteRepository.findAll().stream().map(Cliente::clienteInputDtoToOutputDto).toList();
    }
}
