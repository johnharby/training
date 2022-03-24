package com.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZerosArray {
    public static void main(String[] args) {
        int[] arr = {1, 0, 3, 0, 4, 5, 0};
        Arrays.stream(arr)
                .forEach(e->System.out.print(e + " "));
        System.out.println();
        int cnt = 0;
        List<Integer> resList = new ArrayList<>();
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] != 0) {
                resList.add(arr[i]);
                cnt++;
            }
        }
        for (int i = cnt; i < arr.length; ++i) {
            resList.add(0);
        }
        System.out.println(resList);
    }
}
