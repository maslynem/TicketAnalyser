package com.example.TicketAnalyser.util.file.reader.impl;

import com.example.TicketAnalyser.dto.TicketDto;
import com.example.TicketAnalyser.dto.TicketsWrapper;
import com.example.TicketAnalyser.exception.json.InvalidJsonException;
import com.example.TicketAnalyser.util.file.reader.TicketFileReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketJsonFileReader implements TicketFileReader {
    private final ObjectMapper objectMapper;

    @Override
    public List<TicketDto> readTickets(InputStream inputStream) {
        try {
            TicketsWrapper ticketsWrapper = objectMapper.readValue(inputStream, TicketsWrapper.class);
            return ticketsWrapper.getTickets();
        } catch (IOException ex) {
            throw new InvalidJsonException(ex);
        }
    }
}
