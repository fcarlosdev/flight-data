package com.flightdata.repository;

import com.flightdata.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirlineRepository extends JpaRepository<Airline,Long> {

    Optional<Airline> findByName(String name);
}
