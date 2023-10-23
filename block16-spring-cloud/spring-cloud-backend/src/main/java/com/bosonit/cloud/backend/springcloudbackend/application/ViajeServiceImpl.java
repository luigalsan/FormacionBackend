package com.bosonit.cloud.backend.springcloudbackend.application;

import com.bosonit.cloud.backend.springcloudbackend.error.EntityNotFoundException;
import com.bosonit.cloud.backend.springcloudbackend.error.UnprocessableEntityException;
import com.bosonit.cloud.backend.springcloudbackend.model.Cliente;
import com.bosonit.cloud.backend.springcloudbackend.model.Viaje;
import com.bosonit.cloud.backend.springcloudbackend.model.dto.ViajeInputDto;
import com.bosonit.cloud.backend.springcloudbackend.model.dto.ViajeOutputDto;
import com.bosonit.cloud.backend.springcloudbackend.repository.ClienteRepository;
import com.bosonit.cloud.backend.springcloudbackend.repository.ViajeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViajeServiceImpl implements ViajeService{
    private static final String EXCEPTION_VIAJE = "La id del viaje no existe";
    @Autowired
    ViajeRepository viajeRepository;

    @Autowired
    ClienteRepository clienteRepository;
    @Override
    @Transactional
    public ViajeOutputDto addViaje(ViajeInputDto viajeInputDto) {
        return viajeRepository.save(new Viaje(viajeInputDto)).viajeInputToViajeOutputDto();
    }

    @Override
    public ViajeOutputDto findViajeById(int id) {
        return viajeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(EXCEPTION_VIAJE)
        ).viajeInputToViajeOutputDto();
    }

    @Override
    public List<ViajeOutputDto> getAllViajes() {
        return viajeRepository.findAll().stream().map(Viaje::viajeInputToViajeOutputDto).toList();
    }

    @Override
    public ViajeOutputDto updateViaje(int id, ViajeInputDto viajeInputDto) {
        Viaje viaje = viajeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(EXCEPTION_VIAJE)
        );

        viaje.setOrigin(viajeInputDto.getOrigin());
        viaje.setDestination(viajeInputDto.getDestination());
        viaje.setDepartureDate(viajeInputDto.getDepartureDate());
        viaje.setArrivalDate(viajeInputDto.getArrivalDate());
        viaje.setStatus(viajeInputDto.isStatus());

        return viajeRepository.save(viaje).viajeInputToViajeOutputDto();
    }

    @Override
    public void deleteViaje(int id) {
        viajeRepository.delete(
                viajeRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException(EXCEPTION_VIAJE)
                )
        );
    }

    @Override
    public void addPasajeroToViaje(int idCliente, int idViaje) {
        Viaje viaje = viajeRepository.findById(idViaje).orElseThrow(
                () -> new EntityNotFoundException(EXCEPTION_VIAJE)
        );

        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(
                () -> new EntityNotFoundException(EXCEPTION_VIAJE)
        );

        if (viaje.getPassenger().size() > 40){
            throw  new UnprocessableEntityException("No quedan plazas para este autobus");
        }

        cliente.setViaje(viaje);
        viaje.getPassenger().add(cliente);
        clienteRepository.save(cliente);
        viajeRepository.save(viaje);
    }

    @Override
    public int countPasajeros(int idViaje) {
        Viaje viaje = viajeRepository.findById(idViaje).orElseThrow(
                () -> new EntityNotFoundException(EXCEPTION_VIAJE)
        );

        return viaje.getPassenger().size();
    }

    @Override
    public boolean getStatusBus(int id) {
        return viajeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(EXCEPTION_VIAJE)
        ).isStatus();
    }

    @Override
    public void updateStatusBus(int id, boolean status) {
        Viaje viaje = viajeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(EXCEPTION_VIAJE)
        );

        viaje.setStatus(status);

        viajeRepository.save(viaje);
    }


}
