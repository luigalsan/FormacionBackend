package com.bosonit.springcloudbackendFrontend.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Viaje {


    private Long id;

    private String origin;

    private String destination;

    private Date departureDate;

    private Date arrivalDate;

    private boolean status;
}