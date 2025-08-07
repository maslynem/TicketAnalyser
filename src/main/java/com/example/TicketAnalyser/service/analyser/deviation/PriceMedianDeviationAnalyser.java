package com.example.TicketAnalyser.service.analyser.deviation;

import com.example.TicketAnalyser.dto.Carrier;
import com.example.TicketAnalyser.dto.CarrierPriceDeviationDto;
import com.example.TicketAnalyser.dto.CityName;
import com.example.TicketAnalyser.dto.TicketDto;
import com.example.TicketAnalyser.service.analyser.deviation.calculator.DeviationCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriceMedianDeviationAnalyser {
    private final DeviationCalculator deviationCalculator;

    public List<CarrierPriceDeviationDto> analysePriceMedianDeviation(List<TicketDto> tickets, CityName originName, CityName destinationName) {
        List<TicketDto> filteredList = getFilteredList(tickets, originName, destinationName);

        Map<Carrier, List<BigDecimal>> pricesByCarrier = filteredList.stream()
                .collect(
                        Collectors.groupingBy(
                                TicketDto::getCarrier,
                                Collectors.mapping(TicketDto::getPrice, Collectors.toList())
                        ));

        return pricesByCarrier.entrySet().stream()
                .map(entry -> new CarrierPriceDeviationDto(entry.getKey(), deviationCalculator.calculateDeviation(entry.getValue())))
                .toList();
    }

    private List<TicketDto> getFilteredList(List<TicketDto> tickets, CityName originName, CityName destinationName) {
        return tickets.stream()
                .filter(ticketDto -> ticketDto.getOriginName() == originName && ticketDto.getDestinationName() == destinationName)
                .toList();
    }

}
