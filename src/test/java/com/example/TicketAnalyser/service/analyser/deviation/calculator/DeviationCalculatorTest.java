package com.example.TicketAnalyser.service.analyser.deviation.calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeviationCalculatorTest {

    @Mock
    private MedianCalculator medianCalculator;

    @Mock
    private AverageCalculator averageCalculator;

    @InjectMocks
    private DeviationCalculator deviationCalculator;

    @Test
    void calculateDeviation_shouldReturnDifferenceBetweenAverageAndMedian() {
        List<BigDecimal> prices = List.of(
                new BigDecimal("10.00"),
                new BigDecimal("20.00"),
                new BigDecimal("30.00")
        );

        when(averageCalculator.calculateAverage(prices))
                .thenReturn(new BigDecimal("20.00"));
        when(medianCalculator.calculateMedian(prices))
                .thenReturn(new BigDecimal("20.00"));

        BigDecimal result = deviationCalculator.calculateDeviation(prices);

        assertEquals(new BigDecimal("0.00"), result);
    }

    @Test
    void calculateDeviation_shouldHandlePositiveDeviation() {
        List<BigDecimal> prices = List.of(
                new BigDecimal("10.00"),
                new BigDecimal("20.00"),
                new BigDecimal("60.00")  // Outlier pulling average up
        );

        when(averageCalculator.calculateAverage(prices))
                .thenReturn(new BigDecimal("30.00"));
        when(medianCalculator.calculateMedian(prices))
                .thenReturn(new BigDecimal("20.00"));

        BigDecimal result = deviationCalculator.calculateDeviation(prices);

        assertEquals(new BigDecimal("10.00"), result);
    }

    @Test
    void calculateDeviation_shouldHandleNegativeDeviation() {
        List<BigDecimal> prices = List.of(
                new BigDecimal("1.00"),
                new BigDecimal("20.00"),
                new BigDecimal("21.00")  // Outlier pulling average down
        );

        when(averageCalculator.calculateAverage(prices))
                .thenReturn(new BigDecimal("14.00"));
        when(medianCalculator.calculateMedian(prices))
                .thenReturn(new BigDecimal("20.00"));

        BigDecimal result = deviationCalculator.calculateDeviation(prices);

        assertEquals(new BigDecimal("-6.00"), result);
    }

    @Test
    void calculateDeviation_shouldHandleEmptyList() {
        List<BigDecimal> prices = List.of();

        when(averageCalculator.calculateAverage(prices))
                .thenReturn(BigDecimal.ZERO);
        when(medianCalculator.calculateMedian(prices))
                .thenReturn(BigDecimal.ZERO);

        BigDecimal result = deviationCalculator.calculateDeviation(prices);

        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    void calculateDeviation_shouldHandleSingleElementList() {
        List<BigDecimal> prices = List.of(new BigDecimal("15.00"));

        when(averageCalculator.calculateAverage(prices))
                .thenReturn(new BigDecimal("15.00"));
        when(medianCalculator.calculateMedian(prices))
                .thenReturn(new BigDecimal("15.00"));

        BigDecimal result = deviationCalculator.calculateDeviation(prices);

        assertEquals(0, result.compareTo(BigDecimal.ZERO));
    }

    @Test
    void calculateDeviation_shouldHandleLargeNumbers() {
        List<BigDecimal> prices = List.of(
                new BigDecimal("1000000000.00"),
                new BigDecimal("2000000000.00"),
                new BigDecimal("3000000000.00")
        );

        when(averageCalculator.calculateAverage(prices))
                .thenReturn(new BigDecimal("2000000000.00"));
        when(medianCalculator.calculateMedian(prices))
                .thenReturn(new BigDecimal("2000000000.00"));

        BigDecimal result = deviationCalculator.calculateDeviation(prices);

        assertEquals(0, result.compareTo(BigDecimal.ZERO));
    }

    @Test
    void calculateDeviation_shouldHandleDecimalPrecision() {
        List<BigDecimal> prices = List.of(
                new BigDecimal("10.505"),
                new BigDecimal("20.505"),
                new BigDecimal("30.505")
        );

        when(averageCalculator.calculateAverage(prices))
                .thenReturn(new BigDecimal("20.51"));
        when(medianCalculator.calculateMedian(prices))
                .thenReturn(new BigDecimal("20.505"));

        BigDecimal result = deviationCalculator.calculateDeviation(prices);

        assertEquals(new BigDecimal("0.005"), result);
    }
}