package com.learn;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class ReadLargeFile {

    public void readLargeFile(String path) throws IOException {
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(path);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // System.out.println(line);
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
    }

    public static void main(String[] args) {
        final String path ="/Users/johnharby/Desktop/1Misc/TheDenial.mp3";
        ReadLargeFile rlf = new ReadLargeFile();
        System.out.println(new Date());
        try {
            rlf.readLargeFile(path);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println(new Date());
    }
}
