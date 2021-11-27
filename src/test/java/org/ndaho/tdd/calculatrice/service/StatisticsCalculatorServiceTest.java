package org.ndaho.tdd.calculatrice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StatisticsCalculatorServiceTest {
//
//    @Spy
//    IntSummaryStatistics summaryStatistics = new IntSummaryStatistics();
//
//    StatisticsCalculatorService underTest;
//
//    @BeforeEach
//    public void setUp() {
//        underTest = new StatisticsCalculatorServiceImpl(summaryStatistics);
//    }
//
//    @Test
//    public void average_shouldSample_allIntegersProvided() {
//        final ArgumentCaptor<Integer> sampleCaptor = ArgumentCaptor.forClass(Integer.class);
//
//        final List<Integer> samples = Arrays.asList(2, 8, 5, 3, 7);
//
//        underTest.average(samples);
//        verify(summaryStatistics, times(samples.size())).accept(sampleCaptor.capture());
//
//        final List<Integer> capturedList = sampleCaptor.getAllValues();
//        assertThat(capturedList).containsExactly(samples.toArray(new Integer[0]));
//    }
//
//    /*
//    Ce deuxième test est moins "unitaire", dans le sens où le test couvre à la fois le calculateur
//    statistique et la classe  IntSummaryStatistics,
//    mais nous ne pouvons pas faire autrement à cause du modificateur final.
//    * */
//    @Test
//    public void average_shouldReturnTheMean_ofAListOfIntegers() {
//        final List<Integer> samples = Arrays.asList(2, 8, 5, 3, 7);
//        final Integer result = underTest.average(samples);
//
//        assertThat(result).isEqualTo(5);
//    }
}