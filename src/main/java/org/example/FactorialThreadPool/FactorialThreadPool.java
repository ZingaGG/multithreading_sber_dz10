package org.example.FactorialThreadPool;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class FactorialThreadPool {
    private static final int POOL_CAP = 10;
    private final BlockingQueue<Integer> integerQueue;
    private final CopyOnWriteArrayList<FactorialThread> threads = new CopyOnWriteArrayList<>();
    private volatile boolean isShutdown = false;

    public FactorialThreadPool(BlockingQueue<Integer> integersFromFile) {
        this.integerQueue = integersFromFile;
    }

    public void start() {
        for (int i = 0; i < POOL_CAP; i++) {
            FactorialThread factorialThread = new FactorialThread();
            threads.add(factorialThread);
            factorialThread.start();
        }
    }

    public void shutdown() {
        isShutdown = true;

        for (FactorialThread thread : threads) {
            thread.interrupt();
        }

        threads.clear();

    }

    private class FactorialThread extends Thread {
        @Override
        public void run() {
            while (!isShutdown) {
                try {
                    Integer number = integerQueue.take();

                    if (isShutdown) {
                        break;
                    }

                    System.out.println("Digit: " + number + ", Factorial: " + calculateFactorial(number));
                    System.out.println(Thread.currentThread());
                } catch (InterruptedException e) {
                    if (isShutdown) {
                        break;
                    }
                    Thread.currentThread().interrupt();
                }
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