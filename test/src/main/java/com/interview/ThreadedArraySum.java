package com.interview;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class ThreadedArraySum {

    static long calc(int[] arr, int numsegs) throws ExecutionException, InterruptedException {
        long totalSum = 0l;
        int size = arr.length / numsegs;
        final int[][] parts = new int[numsegs][size];
        for (int i = 0; i < numsegs; ++i) {
            for (int j = 0; j < size; ++j) {
                parts[i][j] = arr[size * i + j];
            }
        }

//        printMDArray(numsegs, size, parts);

        ForkJoinPool executor = ForkJoinPool.commonPool();
        for (int i = 0; i < numsegs; ++i) {
            final int[] partialArray = parts[i];
            var task = executor.submit(() -> computeArraySum(partialArray));
            totalSum += (long) task.get();
        }
        return totalSum;
    }

    private static void printMDArray(int numsegs, int size, int[][] parts) {
        for (int i = 0; i < numsegs; ++i) {
            for (int j = 0; j < size; ++j) {
                System.out.print(i + "," + j + ":" + parts[i][j] + "  ");
            }
        }
        System.out.println();
    }

    public static int computeArraySum(int[] arr) {
        return Arrays.stream(arr).sum();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int size = 1000000;
        int numsegs = 1000;
        int[] arr = new int[size];
        for (int i = 0; i < size; ++i) {
            arr[i] = i + 1;
        }
        System.out.println("Total is " + calc(arr, numsegs));
    }
}
