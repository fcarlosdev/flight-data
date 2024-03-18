package com.flightdata.service;

import com.flightdata.dto.CrazySupplierFlightResponseDTO;
import com.flightdata.dto.CrazySupplierRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CrazySupplierServiceImpl implements CrazySupplierService {

    private final RestTemplate restTemplate;
    @Value("${crazy-supplier.api-url}")
    private String apiUrl;
    @Value("${jwt_token}")
    private String jwtToken;

    @Override
    public List<CrazySupplierFlightResponseDTO> getCrazySupplierFlight(CrazySupplierRequestDTO requestDTO) {

        try {
            HttpEntity<CrazySupplierRequestDTO> requestEntity = new HttpEntity<>(requestDTO, prepareHeader());
            ResponseEntity<List<CrazySupplierFlightResponseDTO>> flights = restTemplate.exchange(apiUrl, HttpMethod.POST,
                    requestEntity, new ParameterizedTypeReference<>() {
                    });

            return flights.getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private HttpHeaders prepareHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Bearer " + jwtToken);
        return headers;
    }
}
