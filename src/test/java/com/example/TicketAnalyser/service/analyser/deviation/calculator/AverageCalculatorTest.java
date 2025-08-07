package com.example.TicketAnalyser.service.analyser.deviation.calculator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AverageCalculatorTest {

    private final AverageCalculator calculator = new AverageCalculator();

    @Test
    void calculateAverage_shouldReturnZeroForEmptyList() {
        BigDecimal result = calculator.calculateAverage(Collections.emptyList());
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    void calculateAverage_shouldReturnSameValueForSingleElement() {
        BigDecimal value = new BigDecimal("10.50");
        BigDecimal result = calculator.calculateAverage(List.of(value));
        assertEquals(value, result);
    }

    @Test
    void calculateAverage_shouldCalculateCorrectAverageForMultipleElements() {
        List<BigDecimal> prices = Arrays.asList(
                new BigDecimal("10.00"),
                new BigDecimal("20.00"),
                new BigDecimal("30.00")
        );

        BigDecimal expected = new BigDecimal("20.00");
        BigDecimal result = calculator.calculateAverage(prices);

        assertEquals(expected, result);
    }

    @Test
    void calculateAverage_shouldRoundResultToTwoDecimalPlaces() {
        List<BigDecimal> prices = Arrays.asList(
                new BigDecimal("10.505"),
                new BigDecimal("20.505")
        );

        BigDecimal expected = new BigDecimal("15.51");
        BigDecimal result = calculator.calculateAverage(prices);

        assertEquals(expected, result);
    }

    @Test
    void calculateAverage_shouldHandleNegativeNumbers() {
        List<BigDecimal> prices = Arrays.asList(
                new BigDecimal("-10.00"),
                new BigDecimal("20.00"),
                new BigDecimal("30.00")
        );

        BigDecimal expected = new BigDecimal("13.33");
        BigDecimal result = calculator.calculateAverage(prices);

        assertEquals(expected, result);
    }

    @Test
    void calculateAverage_shouldHandleLargeNumbers() {
        List<BigDecimal> prices = Arrays.asList(
                new BigDecimal("1000000000.00"),
                new BigDecimal("2000000000.00"),
                new BigDecimal("3000000000.00")
        );

        BigDecimal expected = new BigDecimal("2000000000.00");
        BigDecimal result = calculator.calculateAverage(prices);

        assertEquals(expected, result);
    }

    @Test
    void calculateAverage_shouldHandleVerySmallNumbers() {
        List<BigDecimal> prices = Arrays.asList(
                new BigDecimal("0.0001"),
                new BigDecimal("0.0002"),
                new BigDecimal("0.0003")
        );

        BigDecimal expected = new BigDecimal("0.00");
        BigDecimal result = calculator.calculateAverage(prices);

        assertEquals(expected, result);
    }
}