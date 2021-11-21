package org.ndaho.tdd.calculatrice.service;

import java.util.Locale;

public class SolutionFormatterImpl implements SolutionFormatter {
    @Override
    public String format(Integer response) {
        return String.format(Locale.FRENCH, "%,d", response);
    }
}
