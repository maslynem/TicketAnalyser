package com.example.TicketAnalyser.service.analyser.deviation.calculator;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MedianCalculator {
    public BigDecimal calculateMedian(List<BigDecimal> prices) {
        if (prices.isEmpty()) return BigDecimal.ZERO;
        prices = new ArrayList<>(prices);
        Collections.sort(prices);
        int size = prices.size();
        if (size % 2 == 0) {
            return prices.get(size / 2 - 1)
                    .add(prices.get(size / 2))
                    .divide(new BigDecimal(2), 2, RoundingMode.HALF_UP);
        } else {
            return prices.get(size / 2);
        }
    }
}
