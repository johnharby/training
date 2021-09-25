package com.learn;

import java.util.Arrays;

public class Quicksort {

    public static void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            // Recursively sort elements of the 2 sub-arrays
            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }

    private static int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin-1);

        for (int j=begin; j<end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        arr = swap(arr, i+1, end);
        return i+1;
    }

    public static int[] swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {1, 99, 4, -84, Integer.MAX_VALUE, 33, 67, Integer.MIN_VALUE, -123, 64646, -938398, 6, 4};
        Quicksort.quickSort(arr, 0, arr.length - 1);
        Arrays.stream(arr).
                asLongStream().
                forEach(e -> System.out.print(e + " "));
    }

}
