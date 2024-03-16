package com.flightdata.service;

import com.flightdata.dto.FlightRequestDTO;
import com.flightdata.dto.FlightResponseDTO;
import com.flightdata.exception.BusinessException;
import com.flightdata.exception.FlightNotFoundException;
import com.flightdata.filter.FlightFilter;
import com.flightdata.model.Flight;

import java.util.List;

public interface FlightService {
    List<FlightResponseDTO> getAllFlights();
    Flight getFlightById(Long id);
    FlightResponseDTO createFlight(FlightRequestDTO flightRequestDTO) throws BusinessException;
    FlightResponseDTO updateFlight(Long id, FlightRequestDTO flightRequestDTO) throws BusinessException;
    void deleteFlight(Long id) throws FlightNotFoundException;
    List<FlightResponseDTO> searchFlights(FlightFilter filter);
}