package com.example.TicketAnalyser;

import com.example.TicketAnalyser.dto.CarrierPriceDeviationDto;
import com.example.TicketAnalyser.dto.CityName;
import com.example.TicketAnalyser.dto.FlightDurationDto;
import com.example.TicketAnalyser.dto.TicketDto;
import com.example.TicketAnalyser.exception.handler.AppExceptionHandler;
import com.example.TicketAnalyser.printer.SystemOutPrinter;
import com.example.TicketAnalyser.service.TicketAnalyser;
import com.example.TicketAnalyser.service.TicketLoader;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TicketAnalyserApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketAnalyserApplication.class, args);
    }


    @Bean
    public ApplicationRunner applicationRunner(TicketLoader ticketLoader,
                                               TicketAnalyser ticketAnalyser,
                                               SystemOutPrinter printer,
                                               AppExceptionHandler exceptionHandler) {
        return args -> {
            try {
                List<TicketDto> ticketsList = ticketLoader.loadTickets(args);
                List<FlightDurationDto> flightDurationList = ticketAnalyser.analyseMinTimeBetween(ticketsList, CityName.VLADIVOSTOK, CityName.TEL_AVIV);
                printer.print("Минимальное время полета между городами Владивосток и Тель-Авив для каждого авиаперевозчика:", flightDurationList);
                List<CarrierPriceDeviationDto> carrierPriceDeviationList = ticketAnalyser.analysePriceMedianDeviation(ticketsList, CityName.VLADIVOSTOK, CityName.TEL_AVIV);
                printer.print("Разница между средней ценой и медианой для полета между городами Владивосток и Тель-Авив для каждого авиаперевозчика:", carrierPriceDeviationList);
            } catch (Exception ex) {
                exceptionHandler.handle(ex);
            }
        };
    }

}
