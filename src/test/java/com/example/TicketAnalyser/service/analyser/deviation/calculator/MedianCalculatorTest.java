package com.example.TicketAnalyser.service.analyser.deviation.calculator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedianCalculatorTest {

    private final MedianCalculator calculator = new MedianCalculator();

    @Test
    void calculateMedian_shouldReturnZeroForEmptyList() {
        BigDecimal result = calculator.calculateMedian(Collections.emptyList());
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    void calculateMedian_shouldReturnSameValueForSingleElement() {
        BigDecimal value = new BigDecimal("10.50");
        BigDecimal result = calculator.calculateMedian(List.of(value));
        assertEquals(value, result);
    }

    @Test
    void calculateMedian_shouldReturnMiddleElementForOddSizedList() {
        List<BigDecimal> prices = Arrays.asList(
                new BigDecimal("10.00"),
                new BigDecimal("20.00"),
                new BigDecimal("30.00")
        );

        BigDecimal expected = new BigDecimal("20.00");
        BigDecimal result = calculator.calculateMedian(prices);

        assertEquals(expected, result);
    }

    @Test
    void calculateMedian_shouldReturnAverageOfMiddleElementsForEvenSizedList() {
        List<BigDecimal> prices = Arrays.asList(
                new BigDecimal("10.00"),
                new BigDecimal("20.00"),
                new BigDecimal("30.00"),
                new BigDecimal("40.00")
        );

        BigDecimal expected = new BigDecimal("25.00");
        BigDecimal result = calculator.calculateMedian(prices);

        assertEquals(expected, result);
    }

    @Test
    void calculateMedian_shouldRoundResultToTwoDecimalPlaces() {
        List<BigDecimal> prices = Arrays.asList(
                new BigDecimal("10.50"),
                new BigDecimal("20.50"),
                new BigDecimal("30.50"),
                new BigDecimal("40.50")
        );

        BigDecimal expected = new BigDecimal("25.50");
        BigDecimal result = calculator.calculateMedian(prices);

        assertEquals(expected, result);
    }

    @Test
    void calculateMedian_shouldHandleNegativeNumbers() {
        List<BigDecimal> prices = Arrays.asList(
                new BigDecimal("-10.00"),
                new BigDecimal("-20.00"),
                new BigDecimal("-30.00")
        );

        BigDecimal expected = new BigDecimal("-20.00");
        BigDecimal result = calculator.calculateMedian(prices);

        assertEquals(expected, result);
    }

    @Test
    void calculateMedian_shouldHandleUnsortedList() {
        List<BigDecimal> prices = Arrays.asList(
                new BigDecimal("30.00"),
                new BigDecimal("10.00"),
                new BigDecimal("20.00")
        );

        BigDecimal expected = new BigDecimal("20.00");
        BigDecimal result = calculator.calculateMedian(prices);

        assertEquals(expected, result);
    }

    @Test
    void calculateMedian_shouldHandleLargeNumbers() {
        List<BigDecimal> prices = Arrays.asList(
                new BigDecimal("1000000000.00"),
                new BigDecimal("2000000000.00"),
                new BigDecimal("3000000000.00")
        );

        BigDecimal expected = new BigDecimal("2000000000.00");
        BigDecimal result = calculator.calculateMedian(prices);

        assertEquals(expected, result);
    }

    @Test
    void calculateMedian_shouldHandleVerySmallNumbers() {
        List<BigDecimal> prices = Arrays.asList(
                new BigDecimal("0.0001"),
                new BigDecimal("0.0002"),
                new BigDecimal("0.0003")
        );

        BigDecimal expected = new BigDecimal("0.0002");
        BigDecimal result = calculator.calculateMedian(prices);

        assertEquals(expected, result);
    }

    @Test
    void calculateMedian_shouldHandleDuplicateValues() {
        List<BigDecimal> prices = Arrays.asList(
                new BigDecimal("10.00"),
                new BigDecimal("10.00"),
                new BigDecimal("20.00"),
                new BigDecimal("20.00")
        );

        BigDecimal expected = new BigDecimal("15.00");
        BigDecimal result = calculator.calculateMedian(prices);

        assertEquals(expected, result);
    }
}