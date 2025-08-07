package com.example.TicketAnalyser.exception.handler;

public interface ExceptionHandler {
    void handle(Exception ex);
    boolean canHandle(Exception ex);
}
