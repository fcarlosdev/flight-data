package com.flightdata.mapper;

import com.flightdata.dto.FlightRequestDTO;
import com.flightdata.dto.FlightResponseDTO;
import com.flightdata.model.Flight;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {
    public FlightResponseDTO toDTO(Flight flight) {
        return FlightResponseDTO.builder()
                .id(flight.getId())
                .airline(flight.getAirline())
                .supplier(flight.getSupplier())
                .fare(flight.getFare())
                .departureAirport(flight.getDepartureAirport())
                .destinationAirport(flight.getDestinationAirport())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .build();
    }

    public Flight toEntity(FlightRequestDTO flightRequestDTO) {
        return Flight.builder()
                .airline(flightRequestDTO.getAirline())
                .supplier(flightRequestDTO.getSupplier())
                .fare(flightRequestDTO.getFare())
                .departureAirport(flightRequestDTO.getDepartureAirport())
                .destinationAirport(flightRequestDTO.getDestinationAirport())
                .departureTime(flightRequestDTO.getDepartureTime())
                .arrivalTime(flightRequestDTO.getArrivalTime())
                .build();
    }
}
