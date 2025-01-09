package org.example;

import org.example.FactorialThread.FactorialThread;
import org.example.FileUtils.FileCreator;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integers = FileCreator.createFile();

        for (int number : integers) {
            FactorialThread thread = new FactorialThread(number);
            thread.start();
        }


        // FileCreator.deleteFile();
    }
}