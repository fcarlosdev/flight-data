package com.flightdata.service;

import com.flightdata.dto.CrazySupplierFlightResponseDTO;
import com.flightdata.dto.CrazySupplierRequestDTO;

import java.util.List;

public interface CrazySupplierService {
    List<CrazySupplierFlightResponseDTO> getCrazySupplierFlight(CrazySupplierRequestDTO requestDTO);
}
