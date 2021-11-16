package org.ndaho.tdd.calculatrice.service;


import org.ndaho.tdd.calculatrice.domain.model.CalculationModel;

import java.util.List;
import java.util.stream.Stream;

public interface BatchCalculatorService {
    List<CalculationModel> batchCalculate(Stream<String> operations);
}
