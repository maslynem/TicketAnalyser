package com.example.TicketAnalyser.service.analyser.deviation.calculator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DeviationCalculator {
    private final MedianCalculator medianCalculator;
    private final AverageCalculator averageCalculator;

    public BigDecimal calculateDeviation(List<BigDecimal> prices) {
        BigDecimal average = averageCalculator.calculateAverage(prices);
        BigDecimal median = medianCalculator.calculateMedian(prices);
        return average.subtract(median);
    }
}
