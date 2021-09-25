package com.interview.optumrx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * NameScoring.java
 * @author John Harby
 *
 * Class that reads in a file of names and produces a score given the
 * ordering algorithm specified.
 */
public class NameScoring {
    private static final String DELIMITER = ",";
    private static final int BASE = 1;

    private List<String> nameList;

    public NameScoring(String filePath) {
        nameList = new ArrayList<>();
        loadNames(filePath);
    }

    /**
     * This method will load the names into an ArrayList and sort that list.
     *
     * @param filePath
     */
    private void loadNames(String filePath) {
        File f = new File(filePath);
        Scanner scan = null;
        try {
            scan = new Scanner(f).useDelimiter(DELIMITER);
        } catch (FileNotFoundException e) {
            System.out.println("The file " + filePath + " was not found.");
        }
        String contents = scan.nextLine();
        String[] names = contents.split(",");
        for (String name : names) {
            nameList.add(name.replace("\"", ""));
        }
        Collections.sort(nameList);
    }

    /**
     * This method will return the sum of all scores of all names in the file.
     * It could apply to any algorithm for scoring on each name, the call to
     * scoreOrderAlgo() could be replaced by the other algorithm.
     *
     * @return the sum total of all scores for all names in the file
     */
    private long computeTotalScore() {
        long total = 0;
        int idx = 1;
        for (String s : nameList) {
            total += scoreOrderAlgo(idx, s);
            idx++;
        }
        return total;
    }

    /**
     * This method computes the score for a name as specified in the algorithm.
     * The score is the sum of the alphabetical value of each letter multiplied
     * by the name's position in the list.
     *
     * @param idx
     * @param name
     * @return
     */
    private int scoreOrderAlgo(int idx, String name) {
        char[] ca = name.toLowerCase().toCharArray();
        int sum = 0;
        for (char c : ca) {
            int rank = c - 'a' + BASE;
            sum += rank;
        }
        return idx * sum;
    }

    /**
     * This method will compute the score for a single name. Included for
     * convenience.
     *
     * @param name - A String name for which to compute the score
     * @return int - The computed score value
     */
    private int score(String name) {
        int idx = -1;
        for (int i = 0; i < nameList.size(); ++i) {
            if (name.equals(nameList.get(i))) {
                idx = i + 1;
            }
        }
        if (idx == -1) {
            System.out.println("Name " + name + " not found in file");
        }
        return scoreOrderAlgo(idx, name);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please specify the name file path as a " +
                    "command line argument");
        }
        NameScoring ns = new NameScoring(args[0]);
        System.out.println("Total score in file is: " + ns.computeTotalScore());
    }
}
