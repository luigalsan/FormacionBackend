package com.bosonit.springcloudbackendFrontend.repository;

import com.bosonit.springcloudbackendFrontend.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}