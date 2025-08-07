package com.example.TicketAnalyser.exception.handler.impl;

import com.example.TicketAnalyser.exception.handler.ExceptionHandler;
import com.example.TicketAnalyser.exception.json.InvalidJsonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InvalidJsonExceptionHandler implements ExceptionHandler {
    @Override
    public void handle(Exception ex) {
        log.error("Loaded JSON is invalid", ex);
    }

    @Override
    public boolean canHandle(Exception ex) {
        return ex instanceof InvalidJsonException;
    }
}
