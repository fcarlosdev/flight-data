package com.flightdata.repository.specification;

import com.flightdata.filter.FlightFilter;
import com.flightdata.model.Flight;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class FlightSpecifications {

    public static Specification<Flight> fromFilter(FlightFilter filter) {
        return (root, query, builder) -> {
            Predicate predicate = builder.equal(root.get("airline"), filter.getAirline());
                predicate = builder.and(predicate, builder.equal(root.get("departureAirport"),
                        filter.getDepartureAirport().toUpperCase()));
                predicate = builder.and(predicate, builder.equal(root.get("destinationAirport"),
                        filter.getDestinationAirport().toUpperCase()));
                predicate = builder.and(predicate, builder.greaterThanOrEqualTo(root.get("departureTime"),
                        filter.getDepartureTime()));
                predicate = builder.and(predicate, builder.lessThanOrEqualTo(root.get("arrivalTime"),
                        filter.getArrivalTime()));

            return predicate;
        };
    }
}

