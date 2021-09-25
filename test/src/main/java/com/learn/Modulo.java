package com.learn;

// Java program to find
// (a^b)%m for b very large.
import java.io.*;

class Modulo
{

    // Function to find power
    static long power(long x, long y, long p) {
        long res = 1; // Initialize result

        // Update x if it is more
        // than or equal to p
        x = x % p;

        while (y > 0)
        {
            // If y is odd, multiply
            // x with the result
            if ((y & 1) > 0)
                res = (res * x) % p;

            // y must be even now
            y = y >> 1; // y = y/2
            x = (x * x) % p;
        }
        return res;
    }

    // Driver Code
    public static void main (String[] args)
    {
        long a = 300000000000000L;

// String input as
// b is very large
        String b = "100000000000000000000000000";

        long remainderB = 0;
        long MOD = 1000000007;

// Reduce the number B to a small
// number using Fermat Little
        for (int i = 0; i < b.length(); i++)
            remainderB = (remainderB * 10 +
                    b.charAt(i) - '0') %
                    (MOD - 1);

        System.out.println(power(a, remainderB, MOD));
    }
}

// This code is contributed by anuj_67.

