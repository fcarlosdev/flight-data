package com.flightdata.controller;

import com.flightdata.dto.APIResponse;
import com.flightdata.dto.FlightRequestDTO;
import com.flightdata.dto.FlightResponseDTO;
import com.flightdata.exception.BusinessException;
import com.flightdata.exception.FlightNotFoundException;
import com.flightdata.filter.FlightFilter;
import com.flightdata.model.Flight;
import com.flightdata.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<APIResponse<?>> getAllFlights() {
        List<FlightResponseDTO> flights = flightService.getAllFlights();
        APIResponse<List<FlightResponseDTO>> responseDTO = APIResponse.<List<FlightResponseDTO>>builder()
                .status("Success")
                .results(flights)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @PostMapping
    public ResponseEntity<APIResponse<?>> createFlight(@RequestBody @Valid FlightRequestDTO flightRequestDTO)
            throws BusinessException {
        FlightResponseDTO flight = flightService.createFlight(flightRequestDTO);
        APIResponse<FlightResponseDTO> responseDTO = APIResponse.<FlightResponseDTO>builder()
                .status("Success")
                .results(flight)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<?>> updateFlight(@PathVariable Long id, @RequestBody FlightRequestDTO flightRequestDTO)
            throws BusinessException {

        FlightResponseDTO flightResponseDTO = flightService.updateFlight(id, flightRequestDTO);

        APIResponse<FlightResponseDTO> apiResponse = APIResponse.<FlightResponseDTO>builder()
                .status("Success")
                .results(flightResponseDTO)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) throws FlightNotFoundException {
        flightService.deleteFlight(id);
    }

    @PostMapping("/search")
    public List<FlightResponseDTO> searchFlights(@RequestBody FlightFilter filter) {
        return flightService.searchFlights(filter);
    }
}