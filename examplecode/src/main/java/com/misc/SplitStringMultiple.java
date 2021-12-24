package com.misc;

import java.util.Arrays;

// From Baeldung
public class SplitStringMultiple {
    public static void main(String[] args) {
        String example = "Mary;Thomas:Jane-Kate";
        String[] names = example.split(";|:|-");
        System.out.println(Arrays.toString(names));
    }
}
