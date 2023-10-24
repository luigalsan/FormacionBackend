package com.bosonit.springcloudbackendFrontend.controller;

import com.bosonit.springcloudbackendFrontend.application.TicketServiceImpl;
import com.bosonit.springcloudbackendFrontend.controller.dto.TicketOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generateTicket")

public class TicketController {

    @Autowired
    TicketServiceImpl ticketService;

    @PostMapping("/{idCliente}/{idViaje}")
    public ResponseEntity<TicketOutputDto> prueba(@PathVariable int idCliente, @PathVariable int idViaje){
        return ResponseEntity.ok().body(ticketService.generateTicket(idCliente,idViaje));
    }
}