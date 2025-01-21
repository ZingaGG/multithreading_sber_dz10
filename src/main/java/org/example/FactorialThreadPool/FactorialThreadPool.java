package org.example.FactorialThreadPool;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class FactorialThreadPool {
    private static final int POOL_CAP = 10;
    private final BlockingQueue<Integer> integerQueue;
    private final List<FactorialThread> threads = new ArrayList<>();

    public FactorialThreadPool(BlockingQueue<Integer> integersFromFile) throws InterruptedException {
        this.integerQueue = integersFromFile;
    }

    public void start() {
        for (int i = 0; i < POOL_CAP; i++) {
            FactorialThread factorialThread = new FactorialThread();
            threads.add(factorialThread);
            factorialThread.start();
        }
    }

    public Integer takeInteger() {
        return integerQueue.poll();
    }

    private class FactorialThread extends Thread {

        @Override
        public void run() {
            while (true) {
                Integer number = takeInteger();

                if(number == null){
                    break;
                }

                System.out.println("Digit: " + number + ", Factorial : " + calculateFactorial(number));
                System.out.println(Thread.currentThread());
            }
        }

        private BigInteger calculateFactorial(int number) {
            BigInteger factorial = BigInteger.ONE;
            for (int i = 2; i <= number; i++) {
                factorial = factorial.multiply(BigInteger.valueOf(i));
            }
            return factorial;
        }
    }
}
