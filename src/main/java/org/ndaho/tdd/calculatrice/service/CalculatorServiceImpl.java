package org.ndaho.tdd.calculatrice.service;


import org.ndaho.tdd.calculatrice.domain.Calculator;
import org.ndaho.tdd.calculatrice.domain.model.CalculationModel;
import org.ndaho.tdd.calculatrice.domain.model.CalculationType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@Named
public class CalculatorServiceImpl implements CalculatorService {

    private final Calculator calculator;
    private SolutionFormatter solutionFormatter;

    @Autowired
    public CalculatorServiceImpl(Calculator calculator, SolutionFormatter solutionFormatter) {
        this.calculator = calculator;
        this.solutionFormatter = solutionFormatter;
    }

    @Override
    public CalculationModel calculate(CalculationModel calculationModel) {
        final CalculationType type = calculationModel.getType();

        Integer response;
        switch (type) {
            case ADDITION:
                response = calculator.add(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
                break;
            case SUBTRACTION:
                response = calculator.sub(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
                break;
            case MULTIPLICATION:
                response = calculator.multiply(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
                break;
            case DIVISION:
                try {
                    response = calculator.divide(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
                } catch (ArithmeticException ex) {
                    throw new IllegalArgumentException(ex.getMessage());
                }
                break;
            default:
                throw new UnsupportedOperationException("Unsupported calculations");
        }

        calculationModel.setSolution(response);
        calculationModel.setFormatedSolution(solutionFormatter.format(response));

        return calculationModel;
    }
}
