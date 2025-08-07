package com.example.TicketAnalyser.exception.handler.impl;

import com.example.TicketAnalyser.exception.args.FileArgumentIsEmptyException;
import com.example.TicketAnalyser.exception.handler.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FileArgumentIsEmptyExceptionHandler implements ExceptionHandler {
    @Override
    public void handle(Exception ex) {
        log.error("Argument '--f' is empty.");
    }

    @Override
    public boolean canHandle(Exception ex) {
        return ex instanceof FileArgumentIsEmptyException;
    }
}
