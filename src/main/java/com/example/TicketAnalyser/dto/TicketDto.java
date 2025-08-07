package com.example.TicketAnalyser.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TicketDto {
    private CityCode origin;

    @JsonProperty("origin_name")
    private CityName originName;

    private CityCode destination;

    @JsonProperty("destination_name")
    private CityName destinationName;

    @JsonFormat(pattern = "dd.MM.yy")
    @JsonProperty("departure_date")
    private LocalDate departureDate;

    @JsonFormat(pattern = "H:mm")
    @JsonProperty("departure_time")
    private LocalTime departureTime;

    @JsonFormat(pattern = "dd.MM.yy")
    @JsonProperty("arrival_date")
    private LocalDate arrivalDate;

    @JsonFormat(pattern = "H:mm")
    @JsonProperty("arrival_time")
    private LocalTime arrivalTime;

    private Carrier carrier;

    private int stops;

    private BigDecimal price;
}
