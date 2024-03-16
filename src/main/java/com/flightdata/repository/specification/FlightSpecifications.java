package com.flightdata.repository.specification;

import com.flightdata.filter.FlightFilter;
import com.flightdata.model.Flight;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class FlightSpecifications {

    public static Specification<Flight> fromFilter(FlightFilter filter) {
        return (root, query, builder) -> {
            Predicate predicate = builder.like(root.get("airline"), "%" + filter.getAirline() + "%");

            if (!filter.getDepartureAirport().isEmpty()) {
                predicate = builder.and(predicate, builder.like(root.get("departureAirport"),
                        "%" + filter.getDepartureAirport().toUpperCase() + "%"));
            }

            if (!filter.getDestinationAirport().isEmpty()) {
                predicate = builder.and(predicate, builder.like(root.get("destinationAirport"),
                        "%" + filter.getDestinationAirport().toUpperCase() + "%"));
            }

            if (filter.getDepartureTime() != null) {
                predicate = builder.and(predicate, builder.equal(root.get("departureTime"), filter.getDepartureTime()));
            }

            if (filter.getArrivalTime() != null) {
                predicate = builder.and(predicate, builder.equal(root.get("arrivalTime"), filter.getArrivalTime()));
            }

            return predicate;
        };
    }
}

