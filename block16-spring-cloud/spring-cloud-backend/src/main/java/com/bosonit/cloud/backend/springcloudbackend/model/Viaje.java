package com.bosonit.cloud.backend.springcloudbackend.model;

import com.bosonit.cloud.backend.springcloudbackend.model.dto.ViajeInputDto;
import com.bosonit.cloud.backend.springcloudbackend.model.dto.ViajeOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "viaje")
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "viaje_id")
    private int id;

    @Column(name="origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "departure_date")
    private Date departureDate;

    @Column(name = "arrival_date")
    private Date arrivalDate;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "viaje", cascade = CascadeType.REMOVE)
    private Set<Cliente> passenger;

    public Viaje(ViajeInputDto viajeInputDto){
        this.origin = viajeInputDto.getOrigin();
        this.destination = viajeInputDto.getDestination();
        this.departureDate = viajeInputDto.getDepartureDate();
        this.arrivalDate = viajeInputDto.getArrivalDate();
        this.status = viajeInputDto.isStatus();
    }

    public ViajeOutputDto viajeInputToViajeOutputDto(){
        return new ViajeOutputDto(
                this.id,
                this.origin,
                this.destination,
                this.departureDate,
                this.arrivalDate,
                this.status
        );
    }
}
