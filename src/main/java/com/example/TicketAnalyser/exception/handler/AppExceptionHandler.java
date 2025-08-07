package com.example.TicketAnalyser.exception.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppExceptionHandler {
    private final List<ExceptionHandler> exceptionHandlerList;

    public void handle(Exception ex) {
        boolean handled = false;
        for (ExceptionHandler exceptionHandler : exceptionHandlerList) {
            if (exceptionHandler.canHandle(ex)) {
                exceptionHandler.handle(ex);
                handled = true;
                break;
            }
        }
        if (!handled) {
            handleUnknown(ex);
        }
    }


    private void handleUnknown(Exception ex) {
        log.error("Unknown exception", ex);
    }
}
