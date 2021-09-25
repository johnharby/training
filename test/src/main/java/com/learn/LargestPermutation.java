package com.learn;

import java.util.Arrays;

public class LargestPermutation {

    static int[] largestPermutation(int k, int[] arr) {
        int len = arr.length;
        int[] tmp = new int[len];
        for (int i = 0; i < len; ++i) {
            tmp[i] = arr[i];
        }
        Arrays.sort(tmp);
        for (int i = 0; i < k; ++i) {
            arr = swap(i, len -i - 1, arr);
        }
        return arr;
    }

    static int[] swap(int i, int j, int[] arr) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {4,2,3,5,1};
        int[] ret = largestPermutation(1, arr);
        for (int i = 0; i < ret.length; ++i) {
            System.out.print(ret[i] + " ");
        }
    }

}
