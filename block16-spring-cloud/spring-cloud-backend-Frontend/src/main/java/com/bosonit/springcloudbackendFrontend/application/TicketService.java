package com.bosonit.springcloudbackendFrontend.application;

import com.bosonit.springcloudbackendFrontend.controller.dto.TicketOutputDto;

public interface TicketService {

    TicketOutputDto generateTicket(int userId, int viajeId);
}
