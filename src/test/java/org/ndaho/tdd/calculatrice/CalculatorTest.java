package org.ndaho.tdd.calculatrice;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.ndaho.tdd.calculatrice.domain.Calculator;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    private Calculator calculatorUnderTest;
    private static Instant startedAt;

    @BeforeEach
    public void initCalculator() {
        calculatorUnderTest = new Calculator();
    }

    @AfterEach
    public void cleanCalculator() {
        calculatorUnderTest = null;
    }

    @BeforeAll
    public static void initStartingTime() {
        System.out.println("Appel avant tous les tests");
        startedAt = Instant.now();
        System.out.println("Start at " + startedAt);
    }

    @AfterAll
    public static void showTestDuration() {
        System.out.println("Appel Après tous les tests");
        Instant endedAt = Instant.now();
        final long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
    }


    @Test
    public void testAddTwoPostiveNumbers() {
//        ARRANGE
        final int a = 2;
        final int b = 5;

//        ACT
        final int somme = calculatorUnderTest.add(a, b);

//        ASSERT
        assertThat(somme).isEqualTo(7);
    }

    @Test
    public void testMultiplyTwoNumbers() {
        //        ARRANGE
        final int a = -2;
        final int b = 5;
        //        ACT
        final int multi = calculatorUnderTest.multiply(a, b);

        //        ASSERT
        assertEquals(-10, multi);
        assertThat(multi).isEqualTo(-10);
    }

    @ParameterizedTest(name = "{0}x0 doit etre égal à 0")
    @ValueSource(ints = {1, 2, 4, 55, 23556})
    public void multiply_shouldReturnZero_ofZeroWithMultipleIntegers(int nbre) {
        //pas arrange--> tout est pret
        //act multiplier par zero
        final int produit = calculatorUnderTest.multiply(nbre, 0);
        //assert
        assertThat(produit).isEqualTo(0);
    }

    @ParameterizedTest(name = "{0}x{1} doit etre égal à {2}")
    @CsvSource({"1, 2, 2", "2, 2, 4", "12, 12, 144"})
    public void multiply_shouldReturnZero_ofZeroWithMultipleIntegers(int nbre1, int nbre2, int expectedResult) {
        //pas arrange--> tout est pret
        //act multiplier two numbers
        final int produit = calculatorUnderTest.multiply(nbre1, nbre2);
        //assert
        assertThat(produit).isEqualTo(expectedResult);
    }

    @Test
    public void listDigits_shouldReturnsTheListOfDigits_ofPositiveInteger() {
        // GIVEN
        int number = -95897;

        // WHEN
        Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

        // THEN
        assertThat(actualDigits).containsExactlyInAnyOrder(5, 7, 8, 9);
    }
}
