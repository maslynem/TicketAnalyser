package com.example.TicketAnalyser.util.file.reader;

import com.example.TicketAnalyser.dto.TicketDto;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface TicketFileReader {
    List<TicketDto> readTickets(InputStream inputStream) throws IOException;
}
