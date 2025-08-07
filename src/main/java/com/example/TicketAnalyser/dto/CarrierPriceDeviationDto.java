package com.example.TicketAnalyser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CarrierPriceDeviationDto {
    private Carrier carrier;
    private BigDecimal deviation;

    @Override
    public String toString() {
        return String.format("Перевозчик: %s, разница: %s",
                carrier != null ? carrier.name() : "null",
                deviation != null ? deviation.toPlainString() : "null");
    }
}
