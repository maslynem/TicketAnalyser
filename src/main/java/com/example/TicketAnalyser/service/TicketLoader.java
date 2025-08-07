package com.example.TicketAnalyser.service;

import com.example.TicketAnalyser.dto.TicketDto;
import com.example.TicketAnalyser.util.file.TicketResourceLoader;
import com.example.TicketAnalyser.util.file.reader.TicketFileReader;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketLoader {
    private final TicketFileReader fileReader;
    private final TicketResourceLoader resourceLoader;

    public List<TicketDto> loadTickets(ApplicationArguments args) throws IOException {
        try (InputStream is = resourceLoader.loadResource(args)) {
            return fileReader.readTickets(is);
        }
    }
}
