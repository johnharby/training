package com.learn;

public class Factorial {
    public static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative argument");
        if (n < 2) {
            return 1;
        }
        else {
            return n * factorial(n-1);
        }
    }

    public static void main(String[] args) {
        long factorial5 = factorial(3);
        System.out.println(factorial5);
        System.out.println(3*2);
    }
}
