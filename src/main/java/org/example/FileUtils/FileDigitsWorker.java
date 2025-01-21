package org.example.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileDigitsWorker{
    private final File file;

    protected FileDigitsWorker(File file){
        this.file = file;
    }

    // Inserts into file 5 random digits from 1 to 50
    // Inserts them as a String
    protected boolean insertDigits(){
        try(FileWriter fileWriter = new FileWriter(file)){
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                fileWriter.write(Integer.toString(random.nextInt(1, 50)));
                fileWriter.write(' ');
            }
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Reads the file into List
    protected List<Integer> readDigits(){
        List<Integer> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] numbers = line.split(" ");
                for (String numStr : numbers) {
                    try {
                        result.add(Integer.parseInt(numStr.trim()));
                    } catch (NumberFormatException e) {
                        System.err.println("Wrong digit: " + numStr);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
