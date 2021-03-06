package org.ndaho.tdd.calculatrice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ndaho.tdd.calculatrice.domain.Calculator;
import org.ndaho.tdd.calculatrice.domain.model.CalculationModel;
import org.ndaho.tdd.calculatrice.domain.model.CalculationType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculatorServiceTest {
    /*
   1 .Identifiez un comportement unique que vous testez avec votre classe sous-test (CUT).
   2. Demandez-vous quelles classes sont nécessaires au comportement à tester.(pas de données stp)
   3. Hormis votre CUT, envisagez toutes les autres classes pour le mocking.--->F.I.R.S.T. et du "I" pour le principe d’isolation des tests
   4. Ne mockez pas les classes qui ne servent quasiment qu’à porter des valeurs.
   5. Installez les mocks requis.
   6. Testez votre CUT.
   7.Vérifiez que vos mocks ont été correctement utilisés.
     */
/*
la classe CalculatorService est la classe à tester, on ne mocke pas ;
la classe Calculator est un collaborateur au service et effectue un traitement, on mocke ;
la classe CalculationModel porte uniquement des données en entrée et en sortie, on ne mocke pas !
 */

    @Mock //plus besoin d'initialiser sa valeur
    Calculator calculator;
    @Mock //plus besoin d'initialiser sa valeur
    SolutionFormatter solutionFormatter;

    CalculatorService classUnderTest;

    @BeforeEach //pense bien instancier la CUT pour chaque test!!!!!
    public void init() {
        classUnderTest = new CalculatorServiceImpl(calculator, solutionFormatter);
    }

    @Test
    public void add_returnsTheSum_ofTwoPositiveNumbers() {
        //comme Calculator est un mock et non plus l'objet réel, on doit lui indiquer quoi faire lorsqu'il sera appelé, ce sera dans l'étape Arrange ou Given ;
        //GIVEN
        when(calculator.add(1, 2)).thenReturn(3);

        //ensuite, on effectue le traitement en appelant la méthode calculate, comme c'était fait dans le test initial ;
        //WHEN
        final int result = classUnderTest.calculate(
                new CalculationModel(CalculationType.ADDITION, 1, 2)).getSolution();

        //enfin, dans l'étape Assert ou Then, on vérifie le résultat mais aussi que la classe Calculator a bien été appelée.
        //THEN
        verify(calculator).add(1, 2); //verifie que la classe (mockée) Calculator a été utilisée, en particulier la méthode add(1, 2).
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void add_returnsTheSum_ofTwoNegativeNumbers() {
        //GIVEN --> je simule l'appelle de add
        when(calculator.add(-1, 2)).thenReturn(1);
        //WHEN
        final int result = classUnderTest.calculate(
                        new CalculationModel(CalculationType.ADDITION, -1, 2))
                .getSolution();

        //verifie que la methode add a ete appelé
        verify(calculator, times(1)).add(-1, 2);
        //assert
        assertThat(result).isEqualTo(1);
    }

    //Paramétrez vos mocks avec des paramètres génériques
    @Test
    public void add_returnsTheSum_ofZeroAndZero() {
        //Given
        when(calculator.add(any(Integer.class), any(Integer.class))).thenReturn(0);
        //when
        final int result = classUnderTest.calculate(
                new CalculationModel(CalculationType.ADDITION, 0, 0)).getSolution();
        //ASSERT
        verify(calculator).add(any(Integer.class), any(Integer.class));
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void calculate_shouldThrowIllegalArgumentException_forADivisionBy0() {
        // GIVEN
        when(calculator.divide(1, 0)).thenThrow(new ArithmeticException());

        // WHEN
        //by using Lamdas
        Executable executable = () -> classUnderTest.calculate(
                new CalculationModel(CalculationType.DIVISION, 1, 0));
        assertThrows(IllegalArgumentException.class, executable);

        // THEN
        verify(calculator, times(1)).divide(1, 0);
    }

    @Test
    public void calculate_shouldFormatSolution_forAnAddition() {
        // GIVEN
        when(calculator.add(10000, 3000)).thenReturn(13000);
        when(solutionFormatter.format(13000)).thenReturn("13 000");

        // WHEN
        final String formattedResult = classUnderTest.calculate(
                new CalculationModel(CalculationType.ADDITION, 10000, 3000)).getFormatedSolution();

        // THEN
        assertThat(formattedResult).isEqualTo("13 000");
    }
}