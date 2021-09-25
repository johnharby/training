package demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.StandardOpenOption.APPEND;

public class PrimeCalc {
    private List<Integer> primeSet;
    private int highestNumber = 1_000_000;

    public PrimeCalc(int highestNumber) {
        this.highestNumber = highestNumber;
        primeSet = new ArrayList<>();
    }

    public void populateSet() {
        primeSet.add(2);
        for (int toCheck = 3; toCheck <= highestNumber; toCheck += 2) {
            boolean prime = true;
            double sqroot = Math.sqrt(toCheck);
            for (int j = 0; j < primeSet.size(); ++j) {
                int p = primeSet.get(j);
                if (p > sqroot) {
                    break;
                }
                if (toCheck % p == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                primeSet.add(toCheck);
            }
        }
    }

    /*
//    This method will print all the primes and print PAL after the ones that are
//    palindromes
    public void printSet() {
    Collections.sort(primeSet);
    for (int p : primeSet) {
    if (isPalindrome(p)) {
    System.out.print(p + " PAL,");
    }
    else {
    System.out.print(p + ",");
    }
    }

    }
    */

    private void printPrimeSet() {
        Collections.sort(primeSet);
        int i = 0;
        for (int p : primeSet) {
            System.out.print(p + ",");
            if (i > 1 && i % 1000 == 0) {
                System.out.println();
            }
            ++i;
        }
        System.out.println();
    }

    private void printPrimeSetToFile(String fileName) throws IOException {
        Collections.sort(primeSet);
        int i = 0;
        final File file = new File("/Users/johnharby/" + fileName);
        file.createNewFile();
        for (int p : primeSet) {
            String s = p + ",";
            Files.write(Paths.get("/Users/johnharby/" + fileName), s.getBytes(), APPEND);
        }
    }

    private boolean isPalindrome(Integer p) {
        String x = p.toString();
        StringBuilder sb = new StringBuilder(x);
        sb.reverse();
        if (x.equals(sb.toString())) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Date());
        PrimeCalc pc = new PrimeCalc(1000000);

        pc.populateSet();
        try {
            pc.printPrimeSetToFile("primes.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(new Date());
    }
}
