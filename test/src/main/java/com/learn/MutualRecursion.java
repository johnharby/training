package com.learn;

public class MutualRecursion {

    public static boolean isOdd(int n) {
        if (n<0) throw new IllegalArgumentException();
        return (n == 0) ? false : isEven(n - 1);
    }

    public static boolean isEven(int n) {
        if (n<0) throw new IllegalArgumentException();
        return (n == 0) ? true : isOdd(n - 1);
    }

    public static void main(String[] args) {
        boolean even = isEven(10);
        System.out.println("Is even: " + even);
        boolean odd = isOdd(11);
        System.out.println("Is odd: " + odd);
    }

}


