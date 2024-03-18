package com.flightdata.utils;

import com.flightdata.model.Airline;
import com.flightdata.model.Flight;
import com.flightdata.repository.AirlineRepository;
import com.flightdata.repository.FlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataBaseUtils {

    @Bean
    public CommandLineRunner loadData(FlightRepository repository, AirlineRepository airlineRepository) {
        List<Flight> flights = generateFlights();
        List.of(Airline.builder()
                        .name("American Airlines")
                        .code("AAL")
                        .build(),
                Airline.builder()
                        .name("United Airlines")
                        .code("UAL")
                        .build(),
                Airline.builder()
                        .name("Delta Airlines")
                        .code("DAL")
                        .build(),
                Airline.builder()
                        .name("Porter Airlines")
                        .code("POE")
                        .build())
                .forEach(airlineRepository::save);

        return (args) -> flights.forEach(repository::save);
    }

    private static List<Flight> generateFlights() {

        return List.of(Flight.builder()
                        .airline("American Airlines")
                        .supplier("American Flights")
                        .fare(1500.00)
                        .departureAirport("LAS")
                        .destinationAirport("HOU")
                        .departureTime(LocalDateTime.of(2024, 5, 10, 12, 0, 0))
                        .arrivalTime(LocalDateTime.of(2024, 5, 11, 1, 0, 0))
                        .build(),
                Flight.builder()
                        .airline("American Airlines")
                        .supplier("American Flights")
                        .fare(1000.00)
                        .departureAirport("DAL")
                        .destinationAirport("HOU")
                        .departureTime(LocalDateTime.of(2024, 4, 5, 8, 0, 0))
                        .arrivalTime(LocalDateTime.of(2024, 4, 5, 9, 0, 0))
                        .build(),
                Flight.builder()
                        .airline("Delta Airlines")
                        .supplier("Delta Flights")
                        .fare(2000.00)
                        .departureAirport("WSH")
                        .destinationAirport("NYC")
                        .departureTime(LocalDateTime.of(2024, 4, 5, 11, 0, 0))
                        .arrivalTime(LocalDateTime.of(2024, 4, 5, 11, 40, 0))
                        .build(),
                Flight.builder()
                        .airline("United Airlines")
                        .supplier("United Flights")
                        .fare(3000.00)
                        .departureAirport("FLD")
                        .destinationAirport("LAS")
                        .departureTime(LocalDateTime.of(2024, 5, 5, 11, 0, 0))
                        .arrivalTime(LocalDateTime.of(2024, 5, 6, 12, 0, 0))
                        .build(),
                Flight.builder()
                        .airline("Porter Airlines")
                        .supplier("Porter Flights")
                        .fare(3000.00)
                        .departureAirport("TOR")
                        .destinationAirport("VAN")
                        .departureTime(LocalDateTime.of(2024, 5, 5, 11, 0, 0))
                        .arrivalTime(LocalDateTime.of(2024, 5, 6, 12, 0, 0))
                        .build()
        );
    }

}

