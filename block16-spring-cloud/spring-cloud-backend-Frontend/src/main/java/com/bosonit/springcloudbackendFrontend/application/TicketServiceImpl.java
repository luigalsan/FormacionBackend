package com.bosonit.springcloudbackendFrontend.application;

import com.bosonit.springcloudbackendFrontend.controller.dto.TicketInputDto;
import com.bosonit.springcloudbackendFrontend.controller.dto.TicketOutputDto;
import com.bosonit.springcloudbackendFrontend.model.Cliente;
import com.bosonit.springcloudbackendFrontend.model.Ticket;
import com.bosonit.springcloudbackendFrontend.model.Viaje;
import com.bosonit.springcloudbackendFrontend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TicketRepository ticketRepository;
    @Override
    public TicketOutputDto generateTicket(int userId, int viajeId) {
        String urlCliente = "http://localhost:8081/cliente/" + userId;
        String urlViaje = "http://localhost:8081/viaje/" + viajeId;
        Cliente cliente = restTemplate.getForObject(urlCliente, Cliente.class);
        Viaje viaje = restTemplate.getForObject(urlViaje, Viaje.class);
        if (cliente != null && viaje != null){
            TicketInputDto ticketInputDto = new TicketInputDto(cliente.getId(),cliente.getNombre(),cliente.getApellido(),cliente.getEmail(),viaje.getOrigin(),viaje.getDestination(),
                    viaje.getDepartureDate(),viaje.getArrivalDate());
            return ticketRepository.save(new Ticket(ticketInputDto)).ticketInputToOutputDto();
        } else {
            throw new RuntimeException("Error al encontrar al cliente o el viaje");
        }



    }
}
