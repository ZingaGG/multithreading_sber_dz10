package org.example.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileCreator {

    private static final String PATH = "file.txt";
    private static final File file = new File(PATH);
    private static final FileDigitsWorker fileDigitsWriter = new FileDigitsWorker(file);


    // Create file file.txt and inserts digits into it
    public static List<Integer> createFile(){
        try {
            if(file.createNewFile() && fileDigitsWriter.insertDigits()){
                System.out.println("File has been created and is ready for use!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileDigitsWriter.readDigits();
    }

    // Deletes file
    public static boolean deleteFile(){
        if(file.delete()){
            System.out.println("File has been successfully deleted!");
            return true;
        }

        return false;
    }
}
