package com.learn;

import java.math.BigInteger;
import java.util.stream.Stream;

// From Bloch book
public class Mersenne {
    private static final BigInteger TWO = new BigInteger("2");
    private static final BigInteger ONE = new BigInteger("1");

    static Stream<BigInteger> primes() {
        return Stream.iterate(TWO, BigInteger::nextProbablePrime);
    }

    public static void main(String[] args) {
        primes().map(p -> TWO.pow(p.intValueExact()).subtract(ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
                .forEach(mp -> System.out.println(mp.bitLength() + ":" + mp));
    }
}
