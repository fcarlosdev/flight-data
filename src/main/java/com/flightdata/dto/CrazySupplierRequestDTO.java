package com.flightdata.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CrazySupplierRequestDTO {
    private String from;
    private String to;
    private LocalDate outboundDate;
    private LocalDate inboundDate;
}
