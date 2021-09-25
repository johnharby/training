package com.interview;

public class IntToStringRecursion {
    /**
     * @param n integer to convert to string
     * @param base base for the representation. Requires 2<=base<=10.
     * @return n represented as a string of digits in the specified base, with
     *           a minus sign if n<0.  No unnecessary leading zeros are included.
     */
    public static String stringValue(int n, int base) {
        if (n < 0) { return "-" + stringValue(-n, base); }
        else if (n < base) { return "" + n; }
        else { return stringValue(n/base, base) + "0123456789".charAt(n % base); }
    }

    public static void main(String[] args) {
        System.out.println(stringValue(-123, 10));
        System.out.println(stringValue(170, 6));
    }
}
