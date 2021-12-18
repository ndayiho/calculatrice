package org.ndaho.tdd.calculatrice.service;

import javax.inject.Named;
import java.util.Locale;

@Named
public class SolutionFormatterImpl implements SolutionFormatter {
    @Override
    public String format(Integer response) {
        return String.format(Locale.FRENCH, "%,d", response);
    }
}
