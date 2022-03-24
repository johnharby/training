package com.threads;

// https://www.baeldung.com/java-volatile-variables-thread-safety
public class VolatileExample {
    static volatile int count = 0;

    void increment() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileExample ve = new VolatileExample();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int index=0; index<1000; index++) {
                    ve.increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int index=0; index<1000; index++) {
                    ve.increment();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        // Running repeatedly can show different values for count which illustrates for non-atomic
        // operations volatile doesn't guarantee thread safety.
        System.out.println(count);
    }
}
