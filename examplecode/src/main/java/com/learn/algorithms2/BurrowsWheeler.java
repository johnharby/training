package com.learn.algorithms2;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        String str = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(str);
        int len = csa.length();
        int beg = -1;

        StringBuffer sb = new StringBuffer();
        beg = populateSB(str, csa, beg, sb);

        BinaryStdOut.write(beg);
        BinaryStdOut.write(sb.toString());
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        int beg = BinaryStdIn.readInt();
        String s = BinaryStdIn.readString();
        int len = s.length();
        char[] ca = s.toCharArray();
        int[] cnt = new int[257];
        int[] tmp = new int[len];
        populateArrays(len, ca, cnt, tmp);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; ++i) {
            int x = tmp[beg];
            sb.append(ca[x]);
            beg = x;
        }
        BinaryStdOut.write(sb.toString());
        BinaryStdOut.close();
    }

    private static void populateArrays(int len, char[] ca, int[] cnt, int[] tmp) {
        for (int i = 0; i < len; ++i) {
            cnt[ca[i] + 1]++;
        }
        for (int i = 0; i < 256; ++i) {
            cnt[i+1] = cnt[i] + cnt[i+1];
        }
        for (int i = 0; i < len; ++i) {
            tmp[cnt[ca[i]]++] = i;
        }
    }

    private static int populateSB(String str, CircularSuffixArray csa, int beg, StringBuffer sb) {
        for (int i = 0; i < csa.length(); ++i) {
            if (csa.index(i)==0) {
                beg = i;
            }
            int idx = csa.index(i)-1;
            if (idx<0) {
                idx= csa.length()-1;
            }
            sb.append(str.charAt(idx));
        }
        return beg;
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            transform();
        }
        else if (args[0].equals("+")) {
            inverseTransform();
        }
    }

}
