package com.example.TicketAnalyser.service.analyser.minTime;

import com.example.TicketAnalyser.dto.Carrier;
import com.example.TicketAnalyser.dto.CityName;
import com.example.TicketAnalyser.dto.FlightDurationDto;
import com.example.TicketAnalyser.dto.TicketDto;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MinTimeFlightAnalyserImpl implements MinTimeFlightAnalyser {

    @Override
    public List<FlightDurationDto> analyseMinTimeBetween(List<TicketDto> tickets, CityName originName, CityName destinationName) {
        List<TicketDto> filteredList = getFilteredList(tickets, originName, destinationName);
        Map<Carrier, Duration> carrierMinDurationMap = getCarrierMinDurationMap(filteredList);
        return getFlightDurationDtoList(carrierMinDurationMap);
    }

    private List<TicketDto> getFilteredList(List<TicketDto> tickets, CityName originName, CityName destinationName) {
        return tickets.stream()
                .filter(ticketDto -> ticketDto.getOriginName() == originName && ticketDto.getDestinationName() == destinationName)
                .toList();
    }

    private Map<Carrier, Duration> getCarrierMinDurationMap(List<TicketDto> tickets) {
        Map<Carrier, Duration> carrierDurationMap = new HashMap<>();
        for (TicketDto ticketDto : tickets) {
            Duration flightDuration = getFlightDuration(ticketDto);
            carrierDurationMap.merge(ticketDto.getCarrier(),
                    flightDuration,
                    (existing, newVal) -> existing.compareTo(newVal) < 0 ? existing : newVal);
        }
        return carrierDurationMap;
    }

    private Duration getFlightDuration(TicketDto ticketDto) {
        LocalDateTime departure = LocalDateTime.of(ticketDto.getDepartureDate(), ticketDto.getDepartureTime());
        LocalDateTime arrival = LocalDateTime.of(ticketDto.getArrivalDate(), ticketDto.getArrivalTime());
        return Duration.between(departure, arrival);
    }

    private List<FlightDurationDto> getFlightDurationDtoList(Map<Carrier, Duration> carrierDurationMap) {
        return carrierDurationMap
                .entrySet()
                .stream()
                .map(entry -> new FlightDurationDto(entry.getKey(), entry.getValue()))
                .toList();
    }

}
