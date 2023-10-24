package com.bosonit.springcloudbackendFrontend.model;

import com.bosonit.springcloudbackendFrontend.controller.dto.TicketInputDto;
import com.bosonit.springcloudbackendFrontend.controller.dto.TicketOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket")

public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private Long id;
    @Column(name = "id_pasajero")
    private Long passengerId;
    @Column(name = "nombre_pasajero")
    private String passengerName;
    @Column(name = "apellido_pasajero")
    private String passengerLastName;
    @Column(name = "email_pasajero")
    private String passengerEmail;
    @Column(name = "viaje_Origen")
    private String tripOrigin;
    @Column(name = "viaje_Destino")
    private String tripDestination;
    @Column(name = "fecha_salida")
    private Date departureDate;
    @Column(name = "fecha_llegada")
    private Date arrivalDate;

    public Ticket(TicketInputDto ticketInputDto){
        this.passengerId = ticketInputDto.getPassengerId();
        this.passengerName = ticketInputDto.getPassengerName();
        this.passengerLastName = ticketInputDto.getPassengerLastName();
        this.passengerEmail = ticketInputDto.getPassengerEmail();
        this.tripOrigin = ticketInputDto.getTripOrigin();
        this.tripDestination = ticketInputDto.getTripDestination();
        this.departureDate = ticketInputDto.getDepartureDate();
        this.arrivalDate = ticketInputDto.getArrivalDate();
    }

    public TicketOutputDto ticketInputToOutputDto(){
        return new TicketOutputDto(
                this.passengerId,
                this.passengerName,
                this.passengerLastName,
                this.passengerEmail,
                this.tripOrigin,
                this.tripDestination,
                this.departureDate,
                this.arrivalDate
        );
    }

}