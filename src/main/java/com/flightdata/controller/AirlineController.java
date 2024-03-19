package com.flightdata.controller;

import com.flightdata.model.Airline;
import com.flightdata.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airlines")
public class AirlineController {

    private final AirlineService airlineService;

    @GetMapping
    public List<Airline> getAllAirlines() {
        return airlineService.getAllAirlines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airline> getAirlineById(@PathVariable Long id) {
        Optional<Airline> airline = airlineService.getAirlineById(id);
        return airline.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Airline> createAirline(@RequestBody Airline airline) {
        Airline savedAirline = airlineService.addAirline(airline);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAirline);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airline> updateAirline(@PathVariable Long id, @RequestBody Airline airline) {
        if (airlineService.getAirlineById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        airline.setId(id);
        Airline updatedAirline = airlineService.updateAirline(id,airline);
        return ResponseEntity.ok().body(updatedAirline);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAirline(@PathVariable Long id) {
        if (airlineService.getAirlineById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        airlineService.deleteAirline(id);
        return ResponseEntity.noContent().build();
    }
}

