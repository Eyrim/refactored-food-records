package me.eyrim.foodrecords2;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Scanner;

public class FileHandling {
    public static String readFileToString(String filePath, Context context) {
        StringBuilder readData = new StringBuilder();
        int currentByte;

        try (FileInputStream fis = context.openFileInput(filePath)) {
            // Read the next byte and append it to the stringbuilder if it isn't EOF
            while ((currentByte = fis.read()) != -1) {
                readData.append((char) currentByte);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return readData.toString();
    }
}
