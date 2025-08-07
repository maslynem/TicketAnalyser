package com.example.TicketAnalyser.exception.handler.impl;

import com.example.TicketAnalyser.exception.file.InvalidFileException;
import com.example.TicketAnalyser.exception.handler.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InvalidFileExceptionHandler implements ExceptionHandler {
    @Override
    public void handle(Exception ex) {
        InvalidFileException invalidFileException = (InvalidFileException) ex;
        log.error("File [{}] can't be load", invalidFileException.getFilePath());
    }

    @Override
    public boolean canHandle(Exception ex) {
        return ex instanceof InvalidFileException;
    }
}
