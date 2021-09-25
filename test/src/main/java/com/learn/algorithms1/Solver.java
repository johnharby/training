package com.learn.algorithms1;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Solver {

    private BoardNode goal;

    public Solver(Board initial)
    {
        if (initial == null) {
            throw new IllegalArgumentException("Input board cannot be null");
        }
        BoardOrder order = new BoardOrder();
        MinPQ<BoardNode> pQ = new MinPQ<>(order);
        MinPQ<BoardNode> copyPq = new MinPQ<>(order);
        BoardNode node = new BoardNode(initial);
        BoardNode copyNode = new BoardNode(initial);
        pQ.insert(node);
        copyPq.insert(copyNode);

        BoardNode min = pQ.delMin();
        BoardNode twinMin = copyPq.delMin();

        min = processBoard(pQ, copyPq, min, twinMin);
        if (min.board.isGoal())  goal = min;
        else                     goal = null;
    }

    private BoardNode processBoard(MinPQ<BoardNode> minPq, MinPQ<BoardNode> copyPq, BoardNode min, BoardNode twinMin) {
        while(!min.board.isGoal() && !twinMin.board.isGoal()) {
            for (Board b : min.board.neighbors()) {
                processNeighbors(minPq, min, b);
            }

            for (Board b : twinMin.board.neighbors()) {
                processNeighbors(copyPq, twinMin, b);
            }

            min = minPq.delMin();
            twinMin = copyPq.delMin();
        }
        return min;
    }

    private void processNeighbors(MinPQ<BoardNode> minPq, BoardNode min, Board b) {
        if (min.prev == null || !b.equals(min.prev.board)) {
            BoardNode n = new BoardNode(b);
            n.moves = min.moves + 1;
            n.prev = min;
            minPq.insert(n);
        }
    }

    public boolean isSolvable()
    {
        return goal != null;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves()
    {
        if (!isSolvable()) {
            return -1;
        }
        else {
            return goal.moves;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution()
    {
        if (!isSolvable()) {
            return null;
        }
        List<Board> list = new LinkedList<>();
        for (BoardNode n = goal; n != null; n = n.prev) {
            list.add(n.board);
        }
        return list;
    }

    private class BoardNode {
        private int moves;
        private Board board;
        private BoardNode prev;

        public BoardNode(Board initial) {
            moves = 0;
            prev = null;
            board = initial;
        }
    }

    private class BoardOrder implements Comparator<BoardNode> {
        public int compare(BoardNode a, BoardNode b) {
            int x = a.board.manhattan() + a.moves;
            int y = b.board.manhattan() + b.moves;
            if (x > y)  {
                return 1;
            }
            if (x < y) {
                return -1;
            }
            else {
                return 0;
            }
        }
    }

    public static void main(String[] args)
    {
        // create initial board from file
        int[][] init = {{1,2,0}, {4, 5, 6}, {7, 3, 8}};
        Board initial = new Board(init);
        Solver solver = new Solver(initial);

        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}