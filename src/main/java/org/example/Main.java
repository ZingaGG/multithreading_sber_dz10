package org.example;

import org.example.FactorialThreadPool.FactorialThreadPool;
import org.example.FileUtils.FileCreator;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> integers = FileCreator.createFile();
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(integers);

        FactorialThreadPool factorialThreadPool = new FactorialThreadPool(blockingQueue);
        factorialThreadPool.start();


        FileCreator.deleteFile();
    }
}