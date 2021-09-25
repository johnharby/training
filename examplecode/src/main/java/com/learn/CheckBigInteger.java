package com.learn;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class CheckBigInteger {
    public static void main(String[] args) {
        BigInteger bi1 = BigInteger.ZERO.setBit(63);
        String str = bi1.toString();
        assertEquals(64, bi1.bitLength());
        assertEquals(1, bi1.signum());
        assertEquals("9223372036854775808", bi1.toString());
        assertEquals(BigInteger.ONE,
                bi1.add((BigInteger.valueOf(Long.MAX_VALUE)).multiply(new BigInteger(String.valueOf(-1)))));

        System.out.println(str);
        assertEquals(19, str.length());
        // assertTrue(str.matches("^10{63}$"));
        System.out.println();
        BigInteger bi = BigInteger.valueOf(Long.MAX_VALUE);
        System.out.println(bi);
        System.out.println(bi.multiply(BigInteger.TEN));
    }
}
