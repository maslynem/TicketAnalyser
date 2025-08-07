package com.example.TicketAnalyser.service;

import com.example.TicketAnalyser.dto.CarrierPriceDeviationDto;
import com.example.TicketAnalyser.dto.CityName;
import com.example.TicketAnalyser.dto.FlightDurationDto;
import com.example.TicketAnalyser.dto.TicketDto;
import com.example.TicketAnalyser.service.analyser.MinTimeFlightAnalyser;
import com.example.TicketAnalyser.service.analyser.deviation.PriceMedianDeviationAnalyser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketAnalyser {
    private final MinTimeFlightAnalyser minTimeFlightAnalyser;
    private final PriceMedianDeviationAnalyser priceMedianDeviationAnalyser;

    public List<FlightDurationDto>  analyseMinTimeBetween(List<TicketDto> tickets, CityName origin, CityName destination) {
        return minTimeFlightAnalyser.analyseMinTimeBetween(tickets, origin, destination);
    }

    public List<CarrierPriceDeviationDto> analysePriceMedianDeviation(List<TicketDto> tickets, CityName origin, CityName destination) {
        return priceMedianDeviationAnalyser.analysePriceMedianDeviation(tickets, origin, destination);
    }

}
