package com.learn.algorithms1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    final private int n;
    final private int trials;
    final private double[] thresholds;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials have to be greater than 0");
        }
        this.n = n;
        this.trials = trials;
        thresholds = new double[trials];

        setupThresholds(n, trials);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats s = new PercolationStats(n, trials);

        System.out.println("mean:\t\t\t\t = " + s.mean());
        System.out.println("stddev:\t\t\t\t = " + s.stddev());
        System.out.println("95% confidence interval:\t = " + s.confidenceLo() + ", " + s.confidenceHi());
    }

    private void openRandom(Percolation p) {
        boolean isOpen = true;
        int row = 0;
        int col = 0;
        while (isOpen) {
            row = StdRandom.uniform(1, n + 1);
            col = StdRandom.uniform(1, n + 1);
            isOpen = p.isOpen(row, col);
        }
        p.open(row, col);
    }

    private void setupThresholds(int n, int trials) {
        for (int i = 0; i < trials; ++i) {
            Percolation perc = new Percolation(n);
            int cnt = 0;
            while(!perc.percolates()) {
                openRandom(perc);
                cnt++;
            }
            thresholds[i] = (double) cnt/(n * n);
        }
    }

}
