package org.ndaho.tdd.calculatrice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SolutionFormatterTest {
    SolutionFormatter solutionFormatter;

    @BeforeEach
    public void init() {
        solutionFormatter = new SolutionFormatterImpl();
    }

    @Test
    public void format_returns_formatedString() {
        //Given
        final Integer givenresp = 1500023;
        //when
        final String formatedSoluton = solutionFormatter.format(givenresp);
        //assert
        assertThat(formatedSoluton).isEqualTo("1 500  023");
    }
}