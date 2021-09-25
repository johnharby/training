package com.interview;

public class SumArray extends Thread {
    int idx;
    int[] arr;
    int total = 0;
    int inc;
    public SumArray(int idx, int arr[], int inc) {
        this.idx = idx;
        this.arr = arr;
        this.inc = inc;
    }

    @Override
    public void run() {
        for (int i = idx; i < idx + inc; ++i) {
            total += arr[i];
        }
    }

    public int getTotal() {
        return total;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        SumArray sa = new SumArray(0, arr, 2);
        sa.start();
        System.out.println(sa.getTotal());
    }
}
