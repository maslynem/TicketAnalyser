package com.example.TicketAnalyser.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;

@Getter
@AllArgsConstructor
@Builder
public class FlightDurationDto {
    private Carrier carrier;
    private Duration duration;

    @Override
    public String toString() {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();

        return String.format("Перевозчик: %s, длительность: %d:%02d",
                carrier.name(),
                hours,
                minutes);
    }
}
