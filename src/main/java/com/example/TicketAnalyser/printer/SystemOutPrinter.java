package com.example.TicketAnalyser.printer;

import java.util.List;

public interface SystemOutPrinter {
    void print(String headerMessage, List<?> objectList);
    void print(String headerMessage, Object object);
}
