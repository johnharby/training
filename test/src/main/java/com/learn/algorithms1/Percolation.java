package com.learn.algorithms1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF matrix;
    private WeightedQuickUnionUF colored;
    private int size, upper, lower;
    private boolean[] openSquares;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        init(n);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        doRangeCheck(row, col);

        if (isOpen(row, col)) {
            return;
        }

        int idx = getHorizontal(row, col);
        openSquares[idx] = true;

        if (row == 1) {
            unionGrids(upper, idx);
        }
        if (row == size) {
            matrix.union(lower, idx);
        }
        populateGrids(row, col, idx);
    }

    private void populateGrids(int row, int col, int idx) {
        if (checkRange(row, col - 1) && isOpen(row, col - 1)) {
            int x = getHorizontal(row, col - 1);
            unionGrids(x, idx);
        }

        if (checkRange(row - 1, col) && isOpen(row - 1, col)) {
            int x = getHorizontal(row -1, col);
            unionGrids(x, idx);
        }

        if (checkRange(row, col + 1) && isOpen(row, col + 1)) {
            int x = getHorizontal(row, col + 1);
            unionGrids(x, idx);
        }

        if (checkRange(row + 1, col) && isOpen(row + 1, col)) {
            int x = getHorizontal(row + 1, col);
            unionGrids(x, idx);
        }
    }

    private void unionGrids(int x, int idx) {
        matrix.union(x, idx);
        colored.union(x, idx);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        doRangeCheck(row, col);
        return openSquares[getHorizontal(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int idx = getHorizontal(row, col);
        return colored.connected(row, col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int cnt = 0;
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (isOpen(i, j)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    // does the system percolate?
    public boolean percolates() {
        return matrix.connected(upper, lower);
    }

    private void init(int n) {
        matrix = new WeightedQuickUnionUF(n * n + 2);
        colored = new WeightedQuickUnionUF(n * n + 1);
        this.size = n;
        upper = getHorizontal(n, n) + 1;
        lower = getHorizontal(n, n) + 2;

        openSquares = new boolean[n * n];
    }

    private boolean checkRange(int row, int col) {
        return col > 0 && row > 0 && col <= size && row <= size;
    }

    private void doRangeCheck(int row, int col) {
        if (!checkRange(row, col)) {
            throw new IndexOutOfBoundsException("Matrix values are out of range");
        }
    }

    private int getHorizontal(int row, int col) {
        doRangeCheck(row, col);
        int dim = size * (row -1);
        return (dim + col) - 1;
    }
}
