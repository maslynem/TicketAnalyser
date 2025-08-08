package com.example.TicketAnalyser.service.analyser.minTime;

import com.example.TicketAnalyser.dto.CityName;
import com.example.TicketAnalyser.dto.FlightDurationDto;
import com.example.TicketAnalyser.dto.TicketDto;

import java.util.List;

public interface MinTimeFlightAnalyser {
    List<FlightDurationDto> analyseMinTimeBetween(List<TicketDto> tickets, CityName originName, CityName destinationName);
}
