package com.deanza;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileReadWrite {
    public static void main(String[] args) {
        FileWriter fw = null;
        try {
            File f = new File("/Users/johnharby/deanza/theraven.txt");
            File outf = new File("/Users/johnharby/deanza/outfile.txt");
            Scanner scan = new Scanner(f);
            fw = new FileWriter(outf);
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                fw.write(s + "\n");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fw != null) {
                    fw.flush();
                    fw.close();
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
