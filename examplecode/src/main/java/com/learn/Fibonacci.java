package com.learn;

public class Fibonacci {
    public static long fibonacci(long n) {
        if (n < 0) throw new IllegalArgumentException("Negative argument");
        return (n < 2) ? n : fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        int numFibs = 10;
        for (int i = 0; i < numFibs; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }
}
