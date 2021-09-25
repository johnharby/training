package com.learn.algorithms1;

import edu.princeton.cs.algs4.Queue;
import java.util.Arrays;

public class Board {

    private final int[] matrix;
    private final int dim;
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles)
    {
        this.dim = tiles[0].length;
        matrix = new int[dim * dim];
        for (int i = 0; i < dim; ++i) {
            for (int j = 0; j < dim; ++j) {
                matrix[i * dim + j] = tiles[i][j];
            }
        }
    }

    private Board(int[] matrix) {
        this.dim = (int) Math.sqrt(matrix.length);
        this.matrix = new int[matrix.length];
        System.arraycopy(matrix, 0, this.matrix, 0, matrix.length);
    }

    // string representation of this board
    public String toString()
    {
        StringBuilder sb = new StringBuilder(dim + "\n");
        for (int i = 0; i < matrix.length; ++i) {
            sb.append(String.format("%2d ", matrix[i]));
            if (i > 0 && (i+1) % dim == 0) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension()
    {
        return dim;
    }

    // number of tiles out of place
    public int hamming()
    {
        int count = 0;
        for (int i = 0; i < dim * dim; ++i) {
            if ((matrix[i] != i + 1) && matrix[i] != 0) {
                count++;
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {               // sum of Manhattan distances between blocks and goal
        int sum = 0;
        for (int i = 0; i < dim * dim; i++)
            if (matrix[i] != i + 1 && matrix[i] != 0) {
                sum += manhattan(matrix[i], i);
            }
        return sum;
    }

    private int manhattan(int goal, int current) {
        int x = Math.abs((goal - 1) / dim - current / dim);
        int y = Math.abs((goal - 1) % dim - current % dim);
        return x + y;
    }

    // is this board the goal board?
    public boolean isGoal()
    {
        for (int i = 0; i < dim * dim - 1; ++i) {
            if (matrix[i] != i + 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object y)
    {
        if (y == null) {
            return false;
        }
        if ((y.getClass() != this.getClass())) {
            return false;
        }
        if (y == this) {
            return true;
        }
        Board that = (Board) y;
        return Arrays.equals(this.matrix, that.matrix);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (matrix != null ? Arrays.hashCode(matrix) : 0);
        return hash;
    }

    // all neighboring boards
    public Iterable<Board> neighbors()
    {
        Queue<Board> queue = new Queue<>();
        int emptyIdx = 0;
        boolean fnd = false;

        for (int i = 0; i < matrix.length; ++i) {
            if (matrix[i] == 0) {
                emptyIdx = i;
                fnd = true;
                break;
            }
        }
        if (!fnd) {
            return null;
        }
        int div = emptyIdx / dim;
        int md = emptyIdx % dim;

        populateQueue(queue, emptyIdx, div, md);

        return queue;
    }

    private void populateQueue(Queue<Board> queue, int emptyIdx, int div, int md) {
        Board nbr;
        if (div != 0) {
            nbr = new Board(matrix);
            swap(nbr, emptyIdx, emptyIdx - dim);
            queue.enqueue(nbr);
        }
        if (div != dim - 1) {
            nbr = new Board(matrix);
            swap(nbr, emptyIdx, emptyIdx + dim);
            queue.enqueue(nbr);
        }
        if (md != 0) {
            nbr = new Board(matrix);
            swap(nbr, emptyIdx, emptyIdx - 1);
            queue.enqueue(nbr);
        }
        if (md != dim - 1) {
            nbr = new Board(matrix);
            swap(nbr, emptyIdx, emptyIdx + 1);
            queue.enqueue(nbr);
        }
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin()
    {
        if (dim == 1) {
            return null;
        }
        Board ret = new Board(matrix);
        if (matrix[0] != 0 && matrix[1] != 0) {
            swap(ret, 0, 1);
        }
        else {
            swap(ret, dim, dim + 1);
        }
        return ret;
    }

    private Board swap(Board b, int i, int j) {
        int tmp = b.matrix[j];
        b.matrix[j] = b.matrix[i];
        b.matrix[i] = tmp;
        return b;
    }

}
