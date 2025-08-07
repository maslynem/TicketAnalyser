package com.example.TicketAnalyser.service.analyser.deviation.calculator;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class AverageCalculator {
    public BigDecimal calculateAverage(List<BigDecimal> prices) {
        if (prices.isEmpty()) return BigDecimal.ZERO;

        BigDecimal sum = prices.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return sum.divide(
                new BigDecimal(prices.size()),
                2,
                RoundingMode.HALF_UP
        );
    }
}
