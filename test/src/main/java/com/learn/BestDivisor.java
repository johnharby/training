package com.learn;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BestDivisor {

    private static final Scanner scanner = new Scanner(System.in);

    public static int sumDigits(int i) {
        return i == 0 ? 0 : i % 10 + sumDigits(i / 10);
    }

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        scanner.close();

        int maxsum = 0, div = 0;

        for (int i = 2; i <= n/2; ++i) {
            if (n % i == 0) {
                int s = sumDigits(i);
                if (s > maxsum) {
                    maxsum = s;
                    div = i;
                }
            }
        }
        if (div > 0) {
            System.out.println(div);
        }
        else {
            System.out.println(n);
        }
    }
}

