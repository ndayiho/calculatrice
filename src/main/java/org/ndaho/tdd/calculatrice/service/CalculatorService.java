package org.ndaho.tdd.calculatrice.service;


import org.ndaho.tdd.calculatrice.domain.model.CalculationModel;

public interface CalculatorService {
    /**
     * Effectuer le calcul demandé par un modèle
     *
     * @param calculationModel de calcul
     * @return Modèle de calcul rempli du résultat
     */
	CalculationModel calculate(CalculationModel calculationModel);
}
