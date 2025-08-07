package com.example.TicketAnalyser.exception.args;

public class FileArgumentIsEmptyException extends RuntimeException {
    private final static String defaultMessage = "Argument '-f' is empty";

    public FileArgumentIsEmptyException() {
        super(defaultMessage);
    }
}
