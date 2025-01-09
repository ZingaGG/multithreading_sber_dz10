package org.example.FactorialThread;

import java.math.BigInteger;

public class FactorialThread extends Thread{
    private final int number;

    public FactorialThread(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println("Digit: " + number + ", Factorial: " + calculateFactorial(number));
    }

    private BigInteger calculateFactorial(int number) {
        BigInteger factorial = BigInteger.ONE;
        for (int i = 2; i <= number; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }
}
