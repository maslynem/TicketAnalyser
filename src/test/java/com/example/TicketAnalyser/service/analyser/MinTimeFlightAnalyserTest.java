package com.example.TicketAnalyser.service.analyser;

import com.example.TicketAnalyser.dto.*;
import com.example.TicketAnalyser.service.analyser.minTime.MinTimeFlightAnalyser;
import com.example.TicketAnalyser.service.analyser.minTime.MinTimeFlightAnalyserImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MinTimeFlightAnalyserTest {

    private final MinTimeFlightAnalyser analyser = new MinTimeFlightAnalyserImpl();

    @Test
    void testAnalyseMinTimeBetween_FilteredAndMinDurationsCorrect() {
        TicketDto ticket1 = createTicket("2023-10-01", "10:00", "2023-10-01", "12:00", Carrier.S7, CityName.VLADIVOSTOK, CityName.UFA);
        TicketDto ticket2 = createTicket("2023-10-01", "11:00", "2023-10-01", "12:00", Carrier.S7, CityName.VLADIVOSTOK, CityName.UFA);
        TicketDto ticket3 = createTicket("2023-10-01", "10:00", "2023-10-01", "15:00", Carrier.SU, CityName.VLADIVOSTOK, CityName.UFA);
        TicketDto ticket4 = createTicket("2023-10-01", "12:00", "2023-10-01", "15:00", Carrier.SU, CityName.VLADIVOSTOK, CityName.UFA);
        TicketDto irrelevantTicket = createTicket("2023-10-01", "10:00", "2023-10-01", "11:00", Carrier.BA, CityName.VLADIVOSTOK, CityName.LARNACA);

        List<TicketDto> tickets = List.of(ticket1, ticket2, ticket3, ticket4, irrelevantTicket);

        List<FlightDurationDto> result = analyser.analyseMinTimeBetween(tickets, CityName.VLADIVOSTOK, CityName.UFA);

        assertEquals(2, result.size());

        Map<Carrier, Duration> expectedMap = Map.of(
                Carrier.S7, Duration.ofHours(1),
                Carrier.SU, Duration.ofHours(3)
        );

        for (FlightDurationDto dto : result) {
            Duration expected = expectedMap.get(dto.getCarrier());
            assertNotNull(expected);
            assertEquals(expected.toMinutes(), dto.getDuration().toMinutes());
        }
    }

    @Test
    void testAnalyseMinTimeBetween_EmptyList() {
        List<FlightDurationDto> result = analyser.analyseMinTimeBetween(List.of(), CityName.VLADIVOSTOK, CityName.UFA);
        assertTrue(result.isEmpty());
    }

    @Test
    void testAnalyseMinTimeBetween_OneCarrierOnly() {
        TicketDto ticket1 = createTicket("2023-10-01", "10:00", "2023-10-01", "12:00", Carrier.S7, CityName.VLADIVOSTOK, CityName.UFA);
        TicketDto ticket2 = createTicket("2023-10-01", "11:00", "2023-10-01", "12:30", Carrier.S7, CityName.VLADIVOSTOK, CityName.UFA);

        List<FlightDurationDto> result = analyser.analyseMinTimeBetween(List.of(ticket1, ticket2), CityName.VLADIVOSTOK, CityName.UFA);

        assertEquals(1, result.size());
        assertEquals(Carrier.S7, result.get(0).getCarrier());
        assertEquals(Duration.ofMinutes(90), result.get(0).getDuration());
    }

    @Test
    void testAnalyseMinTimeBetween_DifferentDatesButSameCities() {
        TicketDto ticket1 = createTicket("2023-10-01", "10:00", "2023-10-01", "12:00", Carrier.S7, CityName.VLADIVOSTOK, CityName.UFA);
        TicketDto ticket2 = createTicket("2023-10-02", "11:00", "2023-10-02", "12:00", Carrier.S7, CityName.VLADIVOSTOK, CityName.UFA);

        List<FlightDurationDto> result = analyser.analyseMinTimeBetween(List.of(ticket1, ticket2), CityName.VLADIVOSTOK, CityName.UFA);

        assertEquals(1, result.size());
        assertEquals(Duration.ofMinutes(60), result.get(0).getDuration());
    }

    @Test
    void testAnalyseMinTimeBetween_OnlyOneTicket() {
        TicketDto ticket1 = createTicket("2023-10-01", "10:00", "2023-10-01", "12:00", Carrier.S7, CityName.VLADIVOSTOK, CityName.UFA);

        List<FlightDurationDto> result = analyser.analyseMinTimeBetween(List.of(ticket1), CityName.VLADIVOSTOK, CityName.UFA);

        assertEquals(1, result.size());
        assertEquals(Carrier.S7, result.get(0).getCarrier());
        assertEquals(Duration.ofMinutes(120), result.get(0).getDuration());
    }

    @Test
    void testAnalyseMinTimeBetween_NullOriginOrDestination() {
        List<FlightDurationDto> result = analyser.analyseMinTimeBetween(List.of(), null, CityName.UFA);
        assertTrue(result.isEmpty());

        result = analyser.analyseMinTimeBetween(List.of(), CityName.VLADIVOSTOK, null);
        assertTrue(result.isEmpty());
    }

    private TicketDto createTicket(String departureDate, String departureTime, String arrivalDate, String arrivalTime,
                                   Carrier carrier, CityName origin, CityName destination) {
        return TicketDto.builder()
                .departureDate(LocalDateTime.parse(departureDate + "T" + departureTime).toLocalDate())
                .departureTime(LocalDateTime.parse(departureDate + "T" + departureTime).toLocalTime())
                .arrivalDate(LocalDateTime.parse(arrivalDate + "T" + arrivalTime).toLocalDate())
                .arrivalTime(LocalDateTime.parse(arrivalDate + "T" + arrivalTime).toLocalTime())
                .carrier(carrier)
                .origin(CityCode.VVO)
                .originName(origin)
                .destination(CityCode.UFA)
                .destinationName(destination)
                .stops(0)
                .price(BigDecimal.valueOf(1000))
                .build();
    }
}
