package org.ndaho.tdd.calculatrice;

import java.util.HashSet;
import java.util.Set;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public void longCalculation() {
        try {
            // Attendre 1 seconde
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Set<Integer> digitsSet(int number) {
        String numberAsString = String.valueOf(number);
        Set<Integer> digits = new HashSet<>();
        for (int i = 0; i < numberAsString.length(); i++) {
            if (numberAsString.charAt(i) != '-') {
                digits.add(Integer.parseInt(numberAsString, i, i + 1, 10));
            }
        }

        return digits;
    }
}
