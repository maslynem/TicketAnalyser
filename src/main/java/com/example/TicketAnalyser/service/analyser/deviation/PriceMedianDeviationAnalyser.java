package com.example.TicketAnalyser.service.analyser.deviation;

import com.example.TicketAnalyser.dto.CityName;
import com.example.TicketAnalyser.dto.TicketDto;

import java.math.BigDecimal;
import java.util.List;

public interface PriceMedianDeviationAnalyser {
    BigDecimal analysePriceMedianDeviation(List<TicketDto> tickets, CityName originName, CityName destinationName);
}
