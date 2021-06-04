package org.ndaho.tdd.calculatrice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

public class CalculatorTest {
    private Calculator calculator;
    private static Instant startedAt;

    @BeforeEach
    public  void initCalculator() {
        calculator = new Calculator();
        System.out.println("Appel avant chaque test");
    }

    @AfterEach
    public void cleanCalculator() {
        System.out.println("Appel après chaque test");
        calculator = null;
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
    public  void testAddTwoPostiveNumbers() {
//        ARRANGE
        final int a = 2;
        final int b = 5;

//        ACT
        final int somme = calculator.add(a, b);

//        ASSERT
        assertEquals(7, somme);
    }

    @Test
    public void testMultiplyTwoNumbers() {
        //        ARRANGE
        final int a = -2;
        final int b = 5;
        //        ACT
        final int multi = calculator.multiply(a, b);

        //        ASSERT
        assertEquals(-10, multi);
    }

    @ParameterizedTest(name = "{0}x0 doit etre égal à 0")
    @ValueSource(ints = {1, 2, 4, 55, 23556})
    public void multiply_shouldReturnZero_ofZeroWithMultipleIntegers(int nbre) {
        //pas arrange--> tout est pret
        //act multiplier par zero
        final int produit = calculator.multiply(nbre, 0);
        //assert
        assertEquals(0, produit);
    }

    @ParameterizedTest(name = "{0}x{1} doit etre égal à {2}")
    @CsvSource({"1, 2, 2", "2, 2, 4", "12, 12, 144"})
    public void multiply_shouldReturnZero_ofZeroWithMultipleIntegers(int nbre1, int nbre2, int expectedResult) {
        //pas arrange--> tout est pret
        //act multiplier two numbers
        final int produit = calculator.multiply(nbre1, nbre2);
        //assert
        assertEquals(expectedResult, produit);
    }

    @Timeout(1)
    @Test
    public void longCalcul_shouldComputeInLessThan1Second() {
        // Arrange

        // Act
        calculator.longCalculation();

        // Assert
        // ...
    }

}
