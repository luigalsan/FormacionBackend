package com.bosonit.cloud.backend.springcloudbackend.application;

import com.bosonit.cloud.backend.springcloudbackend.error.EntityNotFoundException;
import com.bosonit.cloud.backend.springcloudbackend.error.UnprocessableEntityException;
import com.bosonit.cloud.backend.springcloudbackend.model.Cliente;
import com.bosonit.cloud.backend.springcloudbackend.model.Viaje;
import com.bosonit.cloud.backend.springcloudbackend.model.dto.ViajeInputDto;
import com.bosonit.cloud.backend.springcloudbackend.model.dto.ViajeOutputDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ViajeService {

    public ViajeOutputDto addViaje(ViajeInputDto viajeInputDto);
    public ViajeOutputDto findViajeById(int id);
    public List<ViajeOutputDto> getAllViajes();
    public ViajeOutputDto updateViaje(int id, ViajeInputDto viajeInputDto);
    public void deleteViaje(int id);
    public void addPasajeroToViaje(int idCliente, int idViaje);
    public int countPasajeros(int idViaje);
    public boolean getStatusBus(int id);
    public void updateStatusBus(int id, boolean status);
}
