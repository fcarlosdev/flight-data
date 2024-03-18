package com.flightdata.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightdata.FlightDataApplication;
import com.flightdata.dto.FlightResponseDTO;
import com.flightdata.filter.FlightFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = FlightDataApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class FlightControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetFlights() throws Exception {
        mockMvc.perform(get("/flights"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.results").isArray())
                .andExpect(jsonPath("$.results").isNotEmpty())
        ;
    }

    @Test
    public void getFlight_whenGivenId() throws Exception {
        mockMvc.perform(get("/flights/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.airline").isNotEmpty());
    }

    @Test
    public void createFlight_whenGivenNewFlight() throws Exception {

        FlightResponseDTO flight = FlightResponseDTO.builder()
                .supplier("Supplier 5")
                .airline("Sky Airlines")
                .fare(4000.0)
                .departureAirport("ABC")
                .destinationAirport("XYZ")
                .departureTime(LocalDateTime.of(2024, 5, 16, 10, 0, 0))
                .arrivalTime(LocalDateTime.of(2024, 5, 10, 10, 40, 0))
                .build();

        mockMvc.perform(post("/flights")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(flight)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.results.id").exists())
                .andExpect(jsonPath("$.results.airline").value("Sky Airlines"))
                .andExpect(jsonPath("$.results.fare").value(4000))
        ;
    }

    @Test
    public void search_whenGivenFilter() throws Exception {

        FlightFilter filter = FlightFilter.builder()
                .airline("American Airlines")
                .departureAirport("DAL")
                .destinationAirport("HOU")
                .departureTime(LocalDateTime.of(2024, 5, 10, 0, 0, 0))
                .arrivalTime(LocalDateTime.of(2024, 5, 20, 0, 0, 0))
                .build();

        mockMvc.perform(post("/flights/search")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(filter)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$").isNotEmpty());
    }

}
