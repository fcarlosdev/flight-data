package com.flightdata.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightRequestDTO {

    private Long id;

    @NotBlank(message = "Airline name is required")
    private String airline;

    @NotBlank(message = "Supplier is required")
    private String supplier;

    @NotNull(message = "Fare is required")
    private Double fare;

    @NotBlank(message = "Departure Airport is required")
    @Size(min = 3, max = 3, message = "Departure Airport must be a 3 letters only")
    private String departureAirport;

    @NotBlank(message = "Destination Airport is required")
    @Size(min = 3, max = 3, message = "Destination Airport must be a 3 letters only")
    private String destinationAirport;

    @NotNull(message = "Departure time is required")
    private LocalDateTime departureTime;

    @NotNull
    @NotNull(message = "Arrival time is required")
    private LocalDateTime arrivalTime;
}
