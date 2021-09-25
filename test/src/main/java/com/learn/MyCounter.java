package com.learn;

public class MyCounter {
    private int count;
    public synchronized void increment() throws InterruptedException {
        int temp = count;
        wait(100);
        count = temp + 1;
    }
    public int getCount() {
        return count;
    }
}
