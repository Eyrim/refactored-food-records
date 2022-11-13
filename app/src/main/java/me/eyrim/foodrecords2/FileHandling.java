package me.eyrim.foodrecords2;

import java.io.File;
import java.util.Scanner;

public class FileHandling {
    public static String readFileToString(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            StringBuilder fileData = new StringBuilder();

            while (scanner.hasNextLine()) {
                fileData.append(scanner.nextLine());
            }

            return fileData.toString();
        } catch (Exception e) {
            e.printStackTrace();

            return "";
        }
    }
}
