package com.example.TicketAnalyser.exception.file;

import lombok.Getter;

@Getter
public class InvalidFileException extends RuntimeException {
    private final String filePath;
    private Throwable cause;

    public InvalidFileException(String filePath, Throwable cause) {
        this.filePath = filePath;
        this.cause = cause;
    }
}
