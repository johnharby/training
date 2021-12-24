package com.threads;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// Parallel streams, Thread pools
public class ThreadPoolExample {

    public void sumWithForkJoinPool() throws InterruptedException {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        ForkJoinPool customThreadPool = new ForkJoinPool(4);
        int sum = 0;
        try {
            sum = customThreadPool.submit(
                    () -> listOfNumbers.parallelStream().reduce(0, Integer::sum)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        customThreadPool.shutdown();
        assert(sum == 10);
    }

    public void parallelRange() {
        System.out.println("Normal...");

        IntStream range = IntStream.rangeClosed(1, 10);
        range.forEach(System.out::print);
        System.out.println();
        System.out.println("Parallel...");

        IntStream range2 = IntStream.rangeClosed(1, 10);
        range2.parallel().forEach(System.out::print);
        System.out.println();
    }

    public void printPrimes() {
        long count = Stream.iterate(0, n -> n + 1)
                .limit(100_000)
                .parallel()  // with this 23s for 1,000,000, without this 1m 10s
                .filter(ThreadPoolExample::isPrime)
                .peek(x -> System.out.format("%s\t", x))
                .count();

        System.out.println("\nTotal: " + count);
    }

    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        return !IntStream.rangeClosed(2, number / 2).anyMatch(i -> number % i == 0);
    }

    public static void main(String[] args) {
        ThreadPoolExample tpe = new ThreadPoolExample();
        try {
            tpe.sumWithForkJoinPool();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tpe.parallelRange();
        tpe.printPrimes();
    }
}

