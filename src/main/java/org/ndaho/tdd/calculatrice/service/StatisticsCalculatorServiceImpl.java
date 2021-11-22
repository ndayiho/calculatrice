package org.ndaho.tdd.calculatrice.service;

import java.util.IntSummaryStatistics;
import java.util.List;

public class StatisticsCalculatorServiceImpl implements StatisticsCalculatorService {
    private final IntSummaryStatistics summaryStatistics;

    public StatisticsCalculatorServiceImpl(IntSummaryStatistics summaryStatistics) {
        this.summaryStatistics = summaryStatistics;
    }

    public Integer average(List<Integer> samples) {
        samples.forEach(summaryStatistics::accept);
        // Extraire la moyenne
        Double average = summaryStatistics.getAverage();
        return average.intValue();
    }
}
