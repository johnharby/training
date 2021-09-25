package com.learn.algorithms2;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        char[] arr = new char[256];
        for (char c = 0; c < 256; ++c) {
            arr[c] = c;
        }
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            char last = arr[0];
            int idx = -1;
            idx = findIdx(arr, c, last, idx);
            BinaryStdOut.write((char) idx);
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        char[] chars = new char[256];
        for (char c = 0; c < 256; ++c) {
            chars[c] = c;
        }

        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();

            BinaryStdOut.write(chars[c], 8);
            char last = chars[0];
            findMatch(chars, c, last);
        }
        BinaryStdOut.close();
    }

    private static void findMatch(char[] chars, char c, char last) {
        for (char d = 0; d < 256; d++) {
            if (d == c) {
                chars[0] = chars[c];
                chars[d] = last;
                break;
            } else {
                char temp = chars[d];
                chars[d] = last;
                last = temp;
            }
        }
    }

    private static int findIdx(char[] arr, char c, char last, int idx) {
        for (char d = 0; d < 256; ++d) {
            if (arr[d] == c) {
                arr[0] = c;
                arr[d] = last;
                idx = d;
                break;
            }
            else {
                char x = arr[d];
                arr[d] = last;
                last = x;
            }
        }
        return idx;
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            encode();
        } else if (args[0].equals("+")) {
            decode();
        }
    }

}
