package com.example.TicketAnalyser.service.analyser.deviation;

import com.example.TicketAnalyser.dto.CityName;
import com.example.TicketAnalyser.dto.TicketDto;
import com.example.TicketAnalyser.service.analyser.deviation.calculator.DeviationCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceMedianDeviationAnalyserImpl implements PriceMedianDeviationAnalyser {
    private final DeviationCalculator deviationCalculator;

    @Override
    public BigDecimal analysePriceMedianDeviation(List<TicketDto> tickets, CityName originName, CityName destinationName) {
        List<BigDecimal> filteredList = getPriceList(tickets, originName, destinationName);
        return deviationCalculator.calculateDeviation(filteredList);
    }

    private List<BigDecimal> getPriceList(List<TicketDto> tickets, CityName originName, CityName destinationName) {
        return tickets.stream()
                .filter(ticketDto -> ticketDto.getOriginName() == originName && ticketDto.getDestinationName() == destinationName)
                .map(TicketDto::getPrice)
                .toList();
    }

}
