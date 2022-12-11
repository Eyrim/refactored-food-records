package me.eyrim.foodrecords2;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class FileHandling {
    public static String readFileToString(String filePath, Context context) {
        /*try (Scanner scanner = new Scanner(new File(filePath))) {
            StringBuilder fileData = new StringBuilder();

            while (scanner.hasNextLine()) {
                fileData.append(scanner.nextLine());
            }

            return fileData.toString();
        } catch (Exception e) {
            e.printStackTrace();

            return "";
        }*/

        try (FileInputStream fis = context.openFileInput("0.json")) {
            int currentByte;
            StringBuilder file = new StringBuilder();

            while ((currentByte = fis.read()) != -1) {
                Byte.
                file.append(currentByte);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return filePath.toString();
    }
}
