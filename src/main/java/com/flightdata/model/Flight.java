package com.flightdata.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "airline")
    private String airline;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "fare")
    private Double fare;

    @Column(name = "departure_airport")
    private String departureAirport;

    @Column(name = "destination_airport")
    private String destinationAirport;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;
}