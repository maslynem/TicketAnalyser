package com.example.TicketAnalyser.service;

import com.example.TicketAnalyser.dto.CityName;
import com.example.TicketAnalyser.dto.FlightDurationDto;
import com.example.TicketAnalyser.dto.TicketDto;
import com.example.TicketAnalyser.service.analyser.deviation.PriceMedianDeviationAnalyser;
import com.example.TicketAnalyser.service.analyser.minTime.MinTimeFlightAnalyser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketAnalyser {
    private final MinTimeFlightAnalyser minTimeFlightAnalyser;
    private final PriceMedianDeviationAnalyser priceMedianDeviationAnalyser;

    public List<FlightDurationDto> analyseMinTimeBetween(List<TicketDto> tickets, CityName origin, CityName destination) {
        return minTimeFlightAnalyser.analyseMinTimeBetween(tickets, origin, destination);
    }

    public BigDecimal analysePriceMedianDeviation(List<TicketDto> tickets, CityName origin, CityName destination) {
        return priceMedianDeviationAnalyser.analysePriceMedianDeviation(tickets, origin, destination);
    }

}
