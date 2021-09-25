package com.learn.algorithms2;

import java.util.Arrays;

public class CircularSuffixArray {

    private final String s;
    private final int[] idx;
    private final int len;

    // circular suffix array of s
    public CircularSuffixArray(final String s) {
        if (s == null) {
            throw new IllegalArgumentException(
                    "Cannot pass null to constructor.");
        }
        this.s = s;
        this.len = s.length();
        StringOffset[] strOffArr = new StringOffset[len];
        for (int i = 0; i < len; i++) {
            strOffArr[i] = new StringOffset(s, i);
        }
        Arrays.sort(strOffArr);
        idx = new int[len];
        for (int i = 0; i < len; i++) {
            idx[i] = strOffArr[i].offset;
        }
    }

    private class StringOffset implements Comparable<StringOffset> {
        private final int offset;
        private final String s;
        public StringOffset(String s, int offset) {
            this.s = s;
            this.offset = offset;
        }
        public int compareTo(StringOffset other) {
            if (this == other) return 0;
            for (int i = 0; i < len; i++) {
                if (this.charAt(i) > other.charAt(i)) return 1;
                if (this.charAt(i) < other.charAt(i)) return -1;
            }
            return 0;
        }
        private char charAt(int pos) {
            return s.charAt((offset + pos) % s.length());
        }
        public String toString() {
            return s.substring(offset, s.length()) + s.substring(0, offset);
        }
    }


    // length of s
    public int length() {
        return s.length();
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i > length() - 1) {
            throw new IllegalArgumentException("Argument passed to index - out of range");
        }
        return idx[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
        String s = "ARD!RCAAAABBARD!RCAAAABB";
        CircularSuffixArray csa = new CircularSuffixArray(s);
        System.out.println("Length: " + csa.length());
        for (int i = 0; i < csa.length(); i++) {
            System.out.println(csa.index(i));
        }
    }

}
