package com.flightdata.service;

import com.flightdata.dto.CrazySupplierFlightResponseDTO;
import com.flightdata.dto.CrazySupplierRequestDTO;
import com.flightdata.dto.FlightRequestDTO;
import com.flightdata.dto.FlightResponseDTO;
import com.flightdata.exception.BusinessException;
import com.flightdata.exception.FlightNotFoundException;
import com.flightdata.filter.FlightFilter;
import com.flightdata.mapper.FlightMapper;
import com.flightdata.model.Airline;
import com.flightdata.model.Flight;
import com.flightdata.repository.AirlineRepository;
import com.flightdata.repository.FlightRepository;
import com.flightdata.repository.specification.FlightSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final CrazySupplierService crazySupplierService;
    private final AirlineRepository airlineRepository;

    @Override
    public List<FlightResponseDTO> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(flightMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    @Override
    public FlightResponseDTO createFlight(FlightRequestDTO flightRequestDTO) throws BusinessException {
        try {
            Flight newFlight = flightMapper.toEntity(flightRequestDTO);
            Flight flight = flightRepository.save(newFlight);
            return flightMapper.toDTO(flight);
        } catch (Exception e) {
            throw new BusinessException("Error when try to create the Flight " + e.getMessage());
        }
    }

    @Override
    public FlightResponseDTO updateFlight(Long id, FlightRequestDTO flightRequestDTO) throws BusinessException {
        try {
            Flight flightToUpdate = flightRepository.findById(id)
                    .orElseThrow(() -> new FlightNotFoundException("Flight not found"));

            flightToUpdate.setAirline(flightRequestDTO.getAirline());
            flightToUpdate.setSupplier(flightRequestDTO.getSupplier());
            flightToUpdate.setFare(flightRequestDTO.getFare());
            flightToUpdate.setDepartureAirport(flightRequestDTO.getDepartureAirport());
            flightToUpdate.setDestinationAirport(flightRequestDTO.getDestinationAirport());
            flightToUpdate.setDepartureTime(flightRequestDTO.getDepartureTime());
            flightToUpdate.setArrivalTime(flightRequestDTO.getArrivalTime());

            Flight flightUpdated = flightRepository.save(flightToUpdate);
            return flightMapper.toDTO(flightUpdated);
        } catch (Exception e) {
            throw new BusinessException("Error when try to update Flight Id: " + id + " " + e.getMessage());
        }
    }

    @Override
    public void deleteFlight(Long id) throws FlightNotFoundException {
        if (flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
        } else {
            throw new FlightNotFoundException("Flight Id: " + id + " not found");
        }
    }

    @Override
    public List<FlightResponseDTO> searchFlights(FlightFilter filter) {

        List<FlightResponseDTO> flights = flightRepository.findAll(FlightSpecifications.fromFilter(filter))
                .stream().map(flightMapper::toDTO)
                .collect(Collectors.toList());

        List<FlightResponseDTO> crazySupplierFlights = getCrazySupplierFlights(filter);

        flights.addAll(crazySupplierFlights);

        return flights;
    }

    private List<FlightResponseDTO> getCrazySupplierFlights(FlightFilter filter) {
        if (!filter.getAirline().isEmpty() && filter.getDepartureTime() != null && filter.getArrivalTime() != null) {

            String airlineCode = airlineRepository.findByName(filter.getAirline())
                    .map(Airline::getCode)
                    .orElse(null);

            CrazySupplierRequestDTO requestDTO = CrazySupplierRequestDTO.builder()
                    .from(airlineCode)
                    .to(airlineCode)
                    .outboundDate(filter.getDepartureTime().toLocalDate())
                    .inboundDate(filter.getArrivalTime().toLocalDate())
                    .build();

            List<CrazySupplierFlightResponseDTO> crazySupplierFlights = crazySupplierService.getCrazySupplierFlight(requestDTO);

            return crazySupplierFlights.stream()
                    .map(responseDTO -> FlightResponseDTO.builder()
                            .airline(responseDTO.getCarrier())
                            .supplier("Crazy Supplier")
                            .fare(responseDTO.getBasePrice() + responseDTO.getTax())
                            .departureAirport(responseDTO.getDepartureAirportName())
                            .destinationAirport(responseDTO.getArrivalAirportName())
                            .departureTime(responseDTO.getOutboundDateTime())
                            .arrivalTime(responseDTO.getInboundDateTime())
                            .build())
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


}
