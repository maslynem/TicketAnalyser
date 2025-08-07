package com.example.TicketAnalyser.printer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemOutPrinterImpl implements SystemOutPrinter {
    @Override
    public void print(String headerMessage, List<?> objectList) {
        System.out.println(headerMessage);
        objectList.forEach(System.out::println);
    }
}
