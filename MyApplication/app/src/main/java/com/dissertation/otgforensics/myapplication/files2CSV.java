package com.dissertation.otgforensics.myapplication;

/**
 * Created by Morgan on 21-Feb-15.
 */


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class files2CSV {


    public static void generateCsvFile(String sFileName, String fileName, String hash) {
        try {
            //FileWriter writer = new FileWriter(sFileName);
            PrintWriter writer = new PrintWriter(new FileWriter(sFileName));
            writer.append(fileName);
            writer.append(',');
            writer.append(hash);
            writer.append('\n');
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




